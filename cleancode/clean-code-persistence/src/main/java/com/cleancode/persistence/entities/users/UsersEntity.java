package com.cleancode.persistence.entities.users;

import com.cleancode.persistence.entities.cardcollections.CardCollectionsEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(name="USERS")
public class UsersEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;
    @Column(name="USER_REFERENCE", unique = true, nullable = false, length=32)
    private String userReference;
    @Column(name = "USERNAME", unique = true, nullable = false, length=32)
    private String userName;
    @Column(name="CCCOIN_WALLET", nullable = false)
    private Long ccCoinWallet = 4L;
    @Column(name="CREATION_DATE", nullable = false)
    private Timestamp creationDate = Timestamp.from(Instant.now());
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CARD_COLLECTION_ID")
    private CardCollectionsEntity userCardCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserReference() {
        return userReference;
    }

    public void setUserReference(String userReference) {
        this.userReference = userReference;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCCCoinWallet() {
        return ccCoinWallet;
    }

    public void setCCCoinWallet(Long userCCCoinWallet) {
        this.ccCoinWallet = userCCCoinWallet;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public CardCollectionsEntity getUserCardCollection() {
        return userCardCollection;
    }

    public void setUserCardCollection(CardCollectionsEntity userCardCollection) {
        this.userCardCollection = userCardCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (!id.equals(that.id)) return false;
        if (!userReference.equals(that.userReference)) return false;
        if (!userName.equals(that.userName)) return false;
        if (!ccCoinWallet.equals(that.ccCoinWallet)) return false;
        if (!creationDate.equals(that.creationDate)) return false;
        return userCardCollection.equals(that.userCardCollection);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userReference.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + ccCoinWallet.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + userCardCollection.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", userReference='" + userReference + '\'' +
                ", userName='" + userName + '\'' +
                ", userCCCoinWallet=" + ccCoinWallet +
                ", creationDate=" + creationDate +
                ", userCardCollection=" + userCardCollection +
                '}';
    }
}
