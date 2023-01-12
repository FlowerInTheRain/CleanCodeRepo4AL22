package com.cleancode.domain.services.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.BusinessCard;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.enums.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.enums.enums.cardpackdistributionsenum.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.enums.enums.cardpacksenum.CardPacksEnum;
import com.cleancode.domain.ports.in.cardpack.CardPackOpener;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardPackOpenerService implements CardPackOpener {

    private final UserAccountPersistencePort userAccountPersistencePort;

    private final CardPersistencePort cardPersistencePort;

    public CardPackOpenerService(UserAccountPersistencePort userAccountPersistencePort, CardPersistencePort cardPersistencePort) {
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.cardPersistencePort = cardPersistencePort;
    }

    @Override
    public List<BusinessCard> openSilverCardPack(String userName) throws CleanCodeException {
        var userAccount = userAccountPersistencePort.findUserByUserName(userName)
                .orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        var cardPack = generateSilverCardPack();
        var packToReturn =  enrichUserCardCollection(userAccount, cardPack);
        userAccountPersistencePort.saveUserInDb(userAccount);
        return packToReturn;
    }

    @Override
    public List<BusinessCard> openDiamondCardPack(String userName) {
        var userAccount = userAccountPersistencePort.findUserByUserName(userName)
                .orElseThrow(() ->  new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        var cardPack = generateDiamondCardPack();
        var packToReturn =  enrichUserCardCollection(userAccount, cardPack);
        userAccountPersistencePort.saveUserInDb(userAccount);
        return packToReturn;
    }

    private List<BusinessCard> enrichUserCardCollection(BusinessUserClientInfo userAccount, List<BusinessCard> cardPack) {
            if(isUserAbleToBuyPack(userAccount.getBusinessUserCCCoinWallet())){
                var userCardCollection = userAccount.getUserCardCollection().getCollectionCardList();
                userCardCollection.addAll( 0, cardPack);
                userAccount.getUserCardCollection().setCollectionCardList(userCardCollection);
            }
        return cardPack;
    }

    public static boolean isUserAbleToBuyPack(long ccCoin){
        return CardPacksEnum.DIAMOND_PACK.getCardPackPrice() <= ccCoin;
    }

    public long computeCardPackBill(long cardPackPrice, BusinessUserClientInfo userAccount){
        return userAccount.getBusinessUserCCCoinWallet() - cardPackPrice;
    }

    private List<BusinessCard> generateSilverCardPack(){
        CardPacksEnum silverPackDetails = CardPacksEnum.SILVER_PACK;
        SilverPackCardRarityDistributionEnum[] silverCardPackDistribution = SilverPackCardRarityDistributionEnum.values();
        long numberOfCardsToCreate = silverPackDetails.getCardsAmount();
        List<BusinessCard> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){
            double randomNumber = Math.random();
            Arrays.stream(silverCardPackDistribution).filter(distribution -> distribution.getMinProbability() >= randomNumber && distribution.getMaxProbability() >= randomNumber)
                    .findFirst()
                    .ifPresent(foundDistribution -> {
                        String rarity = foundDistribution.getCardRarityEnum().getRarityValue();
                        BusinessCard card = cardPersistencePort.findOneCardByRarity(rarity);
                        packContent.add(card);
                    });
        }
        return packContent;
    }

    private List<BusinessCard> generateDiamondCardPack(){
        CardPacksEnum diamondPackDetails = CardPacksEnum.DIAMOND_PACK;
        DiamondPackCardRarityDistributionEnum[] diamondPackCardRarityDistribution = DiamondPackCardRarityDistributionEnum.values();
        long numberOfCardsToCreate = diamondPackDetails.getCardsAmount();
        List<BusinessCard> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){
            double randomNumber = Math.random();
            Arrays.stream(diamondPackCardRarityDistribution).filter(distribution -> distribution.getMinProbability() >= randomNumber && distribution.getMaxProbability() >= randomNumber)
                    .findFirst()
                    .ifPresent(foundDistribution -> {
                        String rarity = foundDistribution.getCardRarityEnum().getRarityValue();
                        BusinessCard card = cardPersistencePort.findOneCardByRarity(rarity);
                        packContent.add(card);
                    });
        }
        return packContent;
    }
}
