package com.cleancode.cleancodedbimpl.entities.users;

import javax.persistence.*;

@Entity
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32, columnDefinition = "A user functional identifier")
    private String userReference;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (!id.equals(that.id)) return false;
        return userReference.equals(that.userReference);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userReference='" + userReference + '\'' +
                '}';
    }
}
