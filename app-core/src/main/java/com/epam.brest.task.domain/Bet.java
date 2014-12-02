package com.epam.brest.task.domain;

import org.joda.time.LocalDateTime;

/**
 * Created by sphincs on 1.12.14.
 */

public class Bet {

    private Long singleBetId;
    private LocalDateTime betDate;
    private String clientName;
    private String passport;
    private double factor;
    private Long betMoney;
    private boolean marker;
    private boolean processed;

    public Bet(){
    };

    public Bet(Long singleBetId, LocalDateTime betDate, String clientName, String passport, double factor, Long betMoney, boolean marker, boolean processed){
        this.singleBetId = singleBetId;
        this.betDate = betDate;
        this.clientName = clientName;
        this.passport = passport;
        this.factor = factor;
        this.betMoney = betMoney;
        this.marker = false;
        this.processed = false;
    }

    public void setSingleBetId(Long singleBetId) {
        this.singleBetId = singleBetId;
    }

    public Long getSingleBetId() {
        return singleBetId;
    }

    public void setBetDate(LocalDateTime betDate) {
        this.betDate = betDate;
    }

    public LocalDateTime getBetDate() {
        return betDate;
    }

    public void setClientName(String clientName ) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public void setBetMoney(Long betMoney) {
        this.betMoney = betMoney;
    }

    public Long getBetMoney() {
        return betMoney;
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
    }

    public boolean isMarker() {
        return marker;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isProcessed() {
        return processed;
    }

    @Override
    public String toString() {
        return "Bet : { name=" + clientName +
                ", passport=" + passport +
                ", factor=" + factor +
                ", betMoney=" + betMoney + "}";
    }
}
