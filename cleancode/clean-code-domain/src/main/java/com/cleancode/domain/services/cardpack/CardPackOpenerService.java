package com.cleancode.domain.services.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.enums.enums.cardpacksenum.CardPacksEnum;
import com.cleancode.domain.ports.in.cardpack.CardOpener;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;

import java.util.ArrayList;
import java.util.List;

public class CardPackOpenerService implements CardOpener {

    private final UserAccountPersistencePort userAccountPersistencePort;

    public CardPackOpenerService(UserAccountPersistencePort userAccountPersistencePort) {
        this.userAccountPersistencePort = userAccountPersistencePort;
    }

    @Override
    public List<Card> openSilverCardPack(String userName) throws CleanCodeException {
        var userAccount = userAccountPersistencePort.findUserByUserName(userName);
        userAccount
                .ifPresent(account -> {
                    if(isUserAbleToBuyPack(account.getBusinessUserCCCoinWallet())){
                        var cardPack = generateSilverCardPack();
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

    public List<Card> generateSilverCardPack(){
        CardPacksEnum silverPackDetails = CardPacksEnum.SILVER_PACK;
        long numberOfCardsToCreate = silverPackDetails.getCardsAmount();
        List<Card> packContent = new ArrayList<>();
        for(long i = 0; i < numberOfCardsToCreate; i++){

        }
        return packContent;
    }
}
