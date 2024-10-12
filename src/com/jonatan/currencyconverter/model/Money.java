package com.jonatan.currencyconverter.model;

public class Money {
    private String simbol;
    private String description;
    private double rate;

    public Money(String simbol, String description) {
        this.simbol = simbol;
        this.description = description;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Money{" +
                "simbol='" + simbol + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}
