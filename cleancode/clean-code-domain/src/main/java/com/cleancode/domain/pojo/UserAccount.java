package com.cleancode.domain.pojo;

import java.sql.Timestamp;

public class UserAccount extends UserBasicInfo {
    protected final Long technicalId;
    private long businessUserCCCoinWallet;
    private Integer businessUserCountWin;
    private String businessReference;
    private Timestamp clientCreationDate;
    private CardCollection userCardCollection;

    public static final Integer WIN_NEEDED_TO_WIN_COIN = 5;
    public static final Integer COIN_GRANTED = 1;

    public UserAccount(String userName, Long technicalId, String businessReference, Integer businessUserCountWin, Timestamp clientCreationDate, CardCollection userCardCollection,
                       Long businessUserCCCoinWallet) {
        super(userName);
        this.technicalId = technicalId;
        setBusinessReference(businessReference);
        setClientCreationDate(clientCreationDate);
        setBusinessUserCCCoinWallet(businessUserCCCoinWallet);
        setBusinessUserCountWin(businessUserCountWin);
        setUserCardCollection(userCardCollection);
    }

    public Integer getBusinessUserCountWin() {
        return businessUserCountWin;
    }

    public void setBusinessUserCountWin(Integer businessUserCountWin) {
        this.businessUserCountWin = businessUserCountWin;
    }

    public void addBusinessUserCountWin() {
        this.businessUserCountWin++;
        if (this.businessUserCountWin % UserAccount.WIN_NEEDED_TO_WIN_COIN == 0) addBusinessUserCCCoinWallet();
    }
    public Long getTechnicalId() {
        return technicalId;
    }
    public String getBusinessReference() {
        return businessReference;
    }
    public void setBusinessReference(String businessReference) {
        this.businessReference = businessReference;
    }
    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }
    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }
    public CardCollection getUserCardCollection() {
        return userCardCollection;
    }
    public void setUserCardCollection(CardCollection userCardCollection) {
        this.userCardCollection = userCardCollection;
    }
    public Long getBusinessUserCCCoinWallet() {
        return businessUserCCCoinWallet;
    }
    public void setBusinessUserCCCoinWallet(Long cardCollectionWallet){
        this.businessUserCCCoinWallet = cardCollectionWallet;
    }
    public void addBusinessUserCCCoinWallet(){
        this.businessUserCCCoinWallet += UserAccount.COIN_GRANTED;
    }
}
