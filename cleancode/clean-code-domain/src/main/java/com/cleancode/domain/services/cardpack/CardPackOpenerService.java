package com.cleancode.domain.services.cardpack;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.pojo.card.CardSpecialty;
import com.cleancode.domain.pojo.enums.cardpackrarities.CardPackRaritiesEnum;
import com.cleancode.domain.pojo.enums.cardpacksenum.CardPacksEnum;
import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;
import com.cleancode.domain.pojo.enums.enums.rarities.RaritiesEnum;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.cardpack.CardPackOpener;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.Probabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.function.LongUnaryOperator;

public class CardPackOpenerService implements CardPackOpener {

    private final UserAccountPersistencePort userAccountPersistencePort;

    private final CardPersistencePort cardPersistencePort;

    private final Probabilities probabilities;

    public CardPackOpenerService(UserAccountPersistencePort userAccountPersistencePort, CardPersistencePort cardPersistencePort, Probabilities probabilities) {
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.cardPersistencePort = cardPersistencePort;
        this.probabilities = probabilities;
    }

    @Override
    public List<CardCollectionCard> openSilverCardPack(String userName) throws CleanCodeException {
        var userAccount = userAccountPersistencePort.findUserByUserName(userName)
                .orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        if(isUserAbleToBuyPack(CardPackRaritiesEnum.SILVER, userAccount.getBusinessUserCCCoinWallet())) {
            var cardPack = generateCardPack(CardPacksEnum.SILVER_PACK, userAccount, probabilities.getSilverProbabilitiesMap());
            enrichUserCardCollection(userAccount, cardPack);
            var newWalletValue = CardPacksEnum.SILVER_PACK.processPayment(userAccount.getBusinessUserCCCoinWallet());
            userAccount.setBusinessUserCCCoinWallet(newWalletValue);
            userAccountPersistencePort.saveUserInDb(userAccount);
            return cardPack;
        }
        throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA);
    }

    @Override
    public List<CardCollectionCard> openDiamondCardPack(String userName) {
        var userAccount = userAccountPersistencePort.findUserByUserName(userName)
                .orElseThrow(() ->  new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        if(isUserAbleToBuyPack(CardPackRaritiesEnum.DIAMOND, userAccount.getBusinessUserCCCoinWallet())) {
            var cardPack = generateCardPack(CardPacksEnum.DIAMOND_PACK, userAccount, probabilities.getDiamondProbabilitiesMap());
            enrichUserCardCollection(userAccount, cardPack);
            var newWallet = processPayment(CardPackRaritiesEnum.DIAMOND.getCardPackPrice(),  userAccount);
            userAccount.setBusinessUserCCCoinWallet(newWallet);
            userAccountPersistencePort.saveUserInDb(userAccount);
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

    public static boolean isUserAbleToBuyPack(CardPackRaritiesEnum ccCoin, Long userWallet){
        return ccCoin.getCardPackPrice() <= userWallet;
    }

    public long processPayment(long cardPackPrice, BusinessUserClientInfo userAccount){
        return LongUnaryOperator.identity().applyAsLong( userAccount.getBusinessUserCCCoinWallet() - cardPackPrice);
    }

    private List<CardCollectionCard> generateCardPack(CardPacksEnum cardPack, BusinessUserClientInfo userAccount, NavigableMap<Double, RaritiesEnum> probabilitiesDistribution){
        long numberOfCardsToCreate = cardPack.getCardsAmount();
        List<CardCollectionCard> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){
            double randomNumber = probabilities.getRandomNumber();
            RaritiesEnum foundDistribution = probabilitiesDistribution.higherEntry(randomNumber).getValue();
            CardCollectionCard cardToAddToCollection = createCardForCollection(foundDistribution, userAccount);
            packContent.add(cardToAddToCollection);
        }
        return packContent;
    }

    private CardCollectionCard createCardForCollection(RaritiesEnum foundDistribution, BusinessUserClientInfo userAccount) {
        String rarity = foundDistribution.getRarityValue();
        CardRarityEnum rarityToUse = CardRarityEnum.valueOf(rarity.toUpperCase());
        Card card = cardPersistencePort.findOneCardByRarity(rarity);
        CardSpecialty specialtyToUse = card.getCardSpecialty().getSpecialtyValue();
        return new CardCollectionCard(
                card.getTechnicalId(),
                userAccount.getUserCardCollection().getTechnicalId(),
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, ""),
                card.getCardName().name(),
                card.getCardSpecialty().getSpecialtyValue().getSpecialty(),
                rarityToUse.getStatValue(specialtyToUse.getLifePoint()),
                rarityToUse.getStatValue(specialtyToUse.getPower()),
                rarityToUse.getStatValue(specialtyToUse.getArmor()),
                0,
                1,
                card.getCardRarity());
    }
}
