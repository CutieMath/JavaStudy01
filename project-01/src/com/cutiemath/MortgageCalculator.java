package com.cutiemath;

import java.text.NumberFormat;

public class MortgageCalculator {

    private int principle;
    private float interestRate;
    private short period;
    private final byte MONTHS_IN_YEAR = 12;
    private final byte PERCENT = 100;


    // Constructor
    public MortgageCalculator(int principle, float interestRate, short period){
        setPrinciple(principle);
        setInterestRate(interestRate);
        setPeriod(period);
    }

    // Setters
    private void setPrinciple(int principle){
        this.principle = principle;
    }
    private void setInterestRate(float interestRate) {
        interestRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
        this.interestRate = interestRate;
    }
    private void setPeriod(short period){
        period *= MONTHS_IN_YEAR;
        this.period = period;
    }

    // Getters
    public short getPeriod(){
        return period;
    }

    // Methods
    public double calculateMortgage() {
        double mortgageRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));
        return mortgageRaw;
    }
    public double calculateBalance(int numberOfPaymentsMade) {
        double balance = principle * ((Math.pow((1 + interestRate), period) - Math.pow((1 + interestRate), numberOfPaymentsMade)) / ( Math.pow((1 + interestRate), period) - 1));
        return balance;
    }
}
