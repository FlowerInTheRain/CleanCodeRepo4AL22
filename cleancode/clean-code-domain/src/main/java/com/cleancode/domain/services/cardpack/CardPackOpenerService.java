package com.cleancode.domain.services.cardpack;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.enums.rarities.CardRarityEnum;
import com.cleancode.domain.enums.rarities.RaritiesEnum;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.pojo.card.CardSpecialty;
import com.cleancode.domain.enums.rarities.CardPackRaritiesEnum;
import com.cleancode.domain.enums.rarities.CardPacksEnum;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.cardpack.CardPackOpener;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.Probabilities;
import com.jnape.palatable.lambda.adt.Maybe;
import com.jnape.palatable.lambda.monad.Monad;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

import static com.jnape.palatable.lambda.adt.hlist.HList.tuple;
import static com.jnape.palatable.lambda.functions.builtin.fn2.InGroupsOf.inGroupsOf;
import static com.jnape.palatable.lambda.functions.builtin.fn2.Take.take;
import static com.jnape.palatable.lambda.functions.builtin.fn2.Unfoldr.unfoldr;

public class CardPackOpenerService implements CardPackOpener {

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
        Maybe<BusinessUserClientInfo> sumOfEvenIncrements = userAccountPersistencePort.findUserByUserName(userName);
        sumOfEvenIncrements.filter(account -> isUserAbleToBuyPack(CardPackRaritiesEnum.SILVER, account.getBusinessUserCCCoinWallet()))
                        .flatMap(account -> {
                    var newWallet = processPayment(CardPackRaritiesEnum.SILVER,  account);
                    account.setBusinessUserCCCoinWallet(newWallet);
                            return Maybe.maybe(account);
                        }).flatMap(account -> {
                            cardPack.addAll(0,generateCardPack(CardPacksEnum.SILVER, account, probabilities.getSilverProbabilitiesMap()));
                            enrichUserCardCollection(account, cardPack);
                            userAccountPersistencePort.saveUserInDb(account);
                          return Maybe.maybe(account);
                }).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA));
        return cardPack;
    }

    @Override
    public List<CardCollectionCard> openDiamondCardPack(String userName) {
        List<CardCollectionCard> cardPack = new ArrayList<>();
        Maybe<BusinessUserClientInfo> sumOfEvenIncrements = userAccountPersistencePort.findUserByUserName(userName);
        sumOfEvenIncrements.filter(account -> isUserAbleToBuyPack(CardPackRaritiesEnum.DIAMOND, account.getBusinessUserCCCoinWallet()))
                .flatMap(account -> {
                    var newWallet = processPayment(CardPackRaritiesEnum.DIAMOND,  account);
                    account.setBusinessUserCCCoinWallet(newWallet);
                    return Maybe.maybe(account);
                }).flatMap(account -> {
                    cardPack.addAll(0,generateCardPack(CardPacksEnum.DIAMOND, account, probabilities.getDiamondProbabilitiesMap()));
                    enrichUserCardCollection(account, cardPack);
                    userAccountPersistencePort.saveUserInDb(account);
                    return Maybe.maybe(account);
                }).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA));
        return cardPack;
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
                rarityToUse.getStatValue(specialtyToUse.getLifePoint()),
                rarityToUse.getStatValue(specialtyToUse.getPower()),
                rarityToUse.getStatValue(specialtyToUse.getArmor()),
                0,
                1,
                card.getCardRarity());
        collectionCardsPort.saveCardInDb(toSave);
        return toSave;
    }
}
