package com.cutiemath;

import java.text.NumberFormat;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;
    private int principle;
    private float interestRate;
    private short period;

    // Constructor
    public MortgageCalculator(int principle, float interestRate, short period){
        setPrinciple(principle);
        setInterestRate(interestRate);
        setPeriod(period);
    }

    // Methods
    public double calculateMortgage() {
        double mortgageRaw = principle * ((getMonthlyInterest() * Math.pow((1 + getMonthlyInterest()), getNumberOfPayments())) / (Math.pow((1 + getMonthlyInterest()), getNumberOfPayments()) - 1));
        return mortgageRaw;
    }
    public double calculateBalance(int numberOfPaymentsMade) {
        double balance = principle * ((Math.pow((1 + getMonthlyInterest()), getNumberOfPayments()) - Math.pow((1 + getMonthlyInterest()), numberOfPaymentsMade)) / ( Math.pow((1 + getMonthlyInterest()), getNumberOfPayments()) - 1));
        return balance;
    }

    public double[] getRemainingBalances(){
        double[] balances = new double[getNumberOfPayments()];
        for(int i = 1; i <= balances.length; i ++) {
            balances[i - 1] = calculateBalance(i);
        }
        return balances;
    }

    // Setters
    private void setPrinciple(int principle){
        this.principle = principle;
    }
    private void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }
    private void setPeriod(short period){
        this.period = period;
    }

    // Getters
    private float getMonthlyInterest(){
        return (interestRate / PERCENT) / MONTHS_IN_YEAR;
    }
    private int getNumberOfPayments(){
        return period * MONTHS_IN_YEAR;
    }
}
