package com.cleancode.domain.services.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.enums.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.enums.enums.cardpackdistributionsenum.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.enums.enums.cardpacksenum.CardPacksEnum;
import com.cleancode.domain.ports.in.cardpack.CardOpener;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;

import java.util.*;

public class CardPackOpenerService implements CardOpener {

    private final UserAccountPersistencePort userAccountPersistencePort;

    private final CardPersistencePort cardPersistencePort;
    public CardPackOpenerService(UserAccountPersistencePort userAccountPersistencePort, CardPersistencePort cardPersistencePort) {
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.cardPersistencePort = cardPersistencePort;
    }

    @Override
    public List<Card> openSilverCardPack(String userName) throws CleanCodeException {
        var userAccount = userAccountPersistencePort.findUserByUserName(userName);
        userAccount
                .ifPresent(account -> {
                    if(isUserAbleToBuyPack(account.getBusinessUserCCCoinWallet())){
                        var cardPack = generateSilverCardPack();
                        var userCardCollection = account.getUserCardCollection().getCollectionCardList();
                        userCardCollection.addAll(userCardCollection.size() - 1, cardPack);
                        account.getUserCardCollection().setCollectionCardList(userCardCollection);
                    }
                });
        return userAccount.orElseThrow(()-> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL)).getUserCardCollection().getCollectionCardList();
    }

    @Override
    public List<Card> openDiamondCardPack(String userReference) {
        return null;
    }

    public static boolean isUserAbleToBuyPack(long ccCoin){
        return CardPacksEnum.DIAMOND_PACK.getCardPackPrice() <= ccCoin;
    }

    public long computeCardPackBill(long cardPackPrice, BusinessUserClientInfo userAccount){
        return userAccount.getBusinessUserCCCoinWallet() - cardPackPrice;
    }

    private List<Card> generateSilverCardPack(){
        CardPacksEnum silverPackDetails = CardPacksEnum.SILVER_PACK;
        SilverPackCardRarityDistributionEnum[] silverCardPackDistribution = SilverPackCardRarityDistributionEnum.values();
        long numberOfCardsToCreate = silverPackDetails.getCardsAmount();
        List<Card> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){
            double randomNumber = Math.random();
            Arrays.stream(silverCardPackDistribution).filter(distribution -> distribution.getMinProbability() >= randomNumber && distribution.getMaxProbability() >= randomNumber)
                    .findFirst()
                    .ifPresent(foundDistribution -> {
                        String rarity = foundDistribution.getCardRarityEnum().getRarityValue();
                        Card card = cardPersistencePort.findOneCardByRarity(rarity);
                        packContent.add(card);
                    });
        }
        return packContent;
    }

    private List<Card> generateDiamondCardPack(){
        CardPacksEnum diamondPackDetails = CardPacksEnum.DIAMOND_PACK;
        DiamondPackCardRarityDistributionEnum[] silverCardPackDistribution = DiamondPackCardRarityDistributionEnum.values();
        long numberOfCardsToCreate = diamondPackDetails.getCardsAmount();
        List<Card> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){
            double randomNumber = Math.random();
            Arrays.stream(silverCardPackDistribution).filter(distribution -> distribution.getMinProbability() >= randomNumber && distribution.getMaxProbability() >= randomNumber)
                    .findFirst()
                    .ifPresent(foundDistribution -> {
                        String rarity = foundDistribution.getCardRarityEnum().getRarityValue();
                        Card card = cardPersistencePort.findOneCardByRarity(rarity);
                        packContent.add(card);
                    });
        }
        return packContent;
    }


}
