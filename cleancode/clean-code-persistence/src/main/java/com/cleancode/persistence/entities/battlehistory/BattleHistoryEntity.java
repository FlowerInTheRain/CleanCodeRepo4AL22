package com.cleancode.persistence.entities.battlehistory;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "BATTLE_HISTORY")
public class BattleHistoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BATTLE_HISTORY_ID")
    private Long id;
    @Column(name = "ATTACKER_USERNAME",  nullable = false, length = 100)
    private String attackerUsername;
    @Column(name = "ATTACKER_CARD_REFERENCE", nullable = false, length = 100)
    private String attackerCard;

    @Column(name = "ATTACKED_USERNAME", nullable = false, length = 100)
    private String attackedUsername;
    @Column(name = "ATTACKED_CARD_REFERENCE", nullable = false, length = 100)
    private String attackedCard;

    @Column(name = "WINNER", nullable = false, length = 100)
    private String winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttackerUsername() {
        return attackerUsername;
    }

    public void setAttackerUsername(String attackerUserName) {
        this.attackerUsername = attackerUserName;
    }

    public String getAttackerCard() {
        return attackerCard;
    }

    public void setAttackerCard(String attackerCard) {
        this.attackerCard = attackerCard;
    }

    public String getAttackedUsername() {
        return attackedUsername;
    }

    public void setAttackedUsername(String attackedUsername) {
        this.attackedUsername = attackedUsername;
    }

    public String getAttackedCard() {
        return attackedCard;
    }

    public void setAttackedCard(String attackedCard) {
        this.attackedCard = attackedCard;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "BattleHistoryEntity{" +
                "id=" + id +
                ", attackerUserName='" + attackerUsername + '\'' +
                ", attackerCard='" + attackerCard + '\'' +
                ", attackedUserName='" + attackedUsername + '\'' +
                ", attackedCard='" + attackedCard + '\'' +
                ", winner='" + winner + '\'' +
                '}';
    }
}
