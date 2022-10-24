package com.cleancode.cleancodedbimpl.entities.users;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class UsersEntity implements Agents{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String userReference;

    @Column(unique = true, nullable = false, length=32)
    private String userName;

    @Column(nullable = false, length=32, columnDefinition = "int default 4")
    private Long userCCCoinWallet;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp creationDate;

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

    @Override
    public String getUserName() {
        return userName;
    }

    @Override

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUserWallet() {
        return userCCCoinWallet;
    }

    public void setUserWallet(Long userCCCoinWallet) {
        this.userCCCoinWallet = userCCCoinWallet;
    }
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userReference='" + userReference + '\'' +
                ", userName='" + userName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", userWallet='" + userCCCoinWallet + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersEntity that = (UsersEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
