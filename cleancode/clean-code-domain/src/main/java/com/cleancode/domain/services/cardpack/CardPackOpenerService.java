package com.cleancode.domain.services.cardpack;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.pojo.enums.rarities.CardPackRaritiesEnum;
import com.cleancode.domain.pojo.enums.rarities.CardPacksEnum;
import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.enums.rarities.RaritiesEnum;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.pojo.card.CardSpecialty;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.cardpack.CardPackOpener;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.probabilities.Probabilities;
import com.google.common.collect.ImmutableSortedMap;
import com.jnape.palatable.lambda.adt.Maybe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardPackOpenerService implements CardPackOpener {
    private static final Logger LOGGER = Logger.getLogger(CardPackOpenerService.class.getName());
    private final UserAccountPersistencePort userAccountPersistencePort;

    private final CardPersistencePort cardPersistencePort;

    private final Probabilities probabilities;

    private final CardCollectionCardPort collectionCardsPort;


    public CardPackOpenerService(UserAccountPersistencePort userAccountPersistencePort, CardPersistencePort cardPersistencePort, Probabilities probabilities, CardCollectionCardPort collectionCardsPort) {
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.cardPersistencePort = cardPersistencePort;
        this.probabilities = probabilities;
        this.collectionCardsPort = collectionCardsPort;
    }

    @Override
    public List<CardCollectionCard> openSilverCardPack(String userName) throws CleanCodeException {
        List<CardCollectionCard> cardPack = new ArrayList<>();
        Maybe<BusinessUserClientInfo> userAccount = userAccountPersistencePort.findUserByUserName(userName);
        var foundUser = userAccount.orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        if(isUserAbleToBuyPack(CardPackRaritiesEnum.SILVER, foundUser.getBusinessUserCCCoinWallet())){
            var newWallet = processPayment(CardPackRaritiesEnum.SILVER,  foundUser);
            foundUser.setBusinessUserCCCoinWallet(newWallet);
            cardPack.addAll(0,generateCardPack(CardPacksEnum.SILVER, foundUser, probabilities.getSilverProbabilitiesMap()));
            enrichUserCardCollection(foundUser, cardPack);
            userAccountPersistencePort.saveUserInDb(foundUser);
            return cardPack;
        }
        throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA);
    }

    @Override
    public List<CardCollectionCard> openDiamondCardPack(String userName) {
        List<CardCollectionCard> cardPack = new ArrayList<>();
        Maybe<BusinessUserClientInfo> userAccount = userAccountPersistencePort.findUserByUserName(userName);
        var foundUser = userAccount.orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        if(isUserAbleToBuyPack(CardPackRaritiesEnum.DIAMOND, foundUser.getBusinessUserCCCoinWallet())){
            var newWallet = processPayment(CardPackRaritiesEnum.DIAMOND,  foundUser);
            foundUser.setBusinessUserCCCoinWallet(newWallet);
            cardPack.addAll(0,generateCardPack(CardPacksEnum.DIAMOND, foundUser, probabilities.getDiamondProbabilitiesMap()));
            enrichUserCardCollection(foundUser, cardPack);
            userAccountPersistencePort.saveUserInDb(foundUser);
            return cardPack;
        }
        throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA);
    }

    private void enrichUserCardCollection(BusinessUserClientInfo userAccount, List<CardCollectionCard> cardPack) {
                List<CardCollectionCard> userCardCollection ;
                if(userAccount.getUserCardCollection().getCollectionCardList() == null){
                    userCardCollection = new ArrayList<>();
                } else {
                    userCardCollection = userAccount.getUserCardCollection().getCollectionCardList();
                }
                userCardCollection.addAll( 0, cardPack);
                userAccount.getUserCardCollection().setCollectionCardList(userCardCollection);
    }

    public static boolean isUserAbleToBuyPack(CardPackRaritiesEnum ccCoin, Long ccCoinWallet){
        return ccCoin.getCardPackPrice() <= ccCoinWallet;
    }

    public long processPayment(CardPackRaritiesEnum cardPackType, BusinessUserClientInfo userAccount){
        long userWallet = userAccount.getBusinessUserCCCoinWallet();
        return CardPacksEnum.valueOf(cardPackType.name())
                .computeBill( userWallet );
    }

    private List<CardCollectionCard> generateCardPack(CardPacksEnum cardPack, BusinessUserClientInfo userAccount, ImmutableSortedMap<Double, RaritiesEnum> probabilitiesDistribution){
        long numberOfCardsToCreate = cardPack.getCardsAmount();
        List<CardCollectionCard> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){
            double randomNumber = probabilities.getRandomNumber();
            RaritiesEnum foundDistribution = Objects.requireNonNull(probabilitiesDistribution.higherEntry(randomNumber)).getValue();
            CardCollectionCard cardToAddToCollection = createCardForCollection(foundDistribution, userAccount);
            packContent.add(cardToAddToCollection);
        }
        LOGGER.info(packContent.toString());
        return packContent;
    }

    private CardCollectionCard createCardForCollection(RaritiesEnum foundDistribution, BusinessUserClientInfo userAccount) {
        String rarity = foundDistribution.name();
        CardRarityEnum rarityToUse = CardRarityEnum.valueOf(rarity.toUpperCase());
        Card card = cardPersistencePort.findOneCardByRarity(rarity);
        CardSpecialty specialtyToUse = card.getCardSpecialty().getSpecialtyValue();
        var toSave =  new CardCollectionCard(
                card.getTechnicalId(),
                userAccount.getUserCardCollection().getTechnicalId(),
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, ""),
                card.getCardName().name(),
                card.getCardSpecialty().getSpecialtyValue().getSpecialty(),
                rarityToUse.computeStatByRarity(specialtyToUse.getLifePoint()),
                rarityToUse.computeStatByRarity(specialtyToUse.getPower()),
                rarityToUse.computeStatByRarity(specialtyToUse.getArmor()),
                0,
                1,
                card.getCardRarity());
        collectionCardsPort.saveCollectionCard(toSave);
        LOGGER.log(Level.INFO,  String.format("Added %s card to %s deck for user %s", toSave, userAccount.getUserCardCollection().getCollectionName(), userAccount.getUserName()));
        return toSave;
    }
}
