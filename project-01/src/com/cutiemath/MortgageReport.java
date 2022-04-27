package com.cutiemath;

import java.text.NumberFormat;

public class MortgageReport {
    private MortgageCalculator calc;
    private final NumberFormat currencyFormatter;

    public MortgageReport(MortgageCalculator calc) {
        this.calc = calc;
        currencyFormatter = NumberFormat.getCurrencyInstance();
    }
    public void printRemainingMortgage(){
        System.out.println();
        System.out.println("REMAINING BALANCE");
        System.out.println("-------------------");
        for (double balance : calc.getRemainingBalances()){
            System.out.println(currencyFormatter.format(balance));
        }
    }
    public void printMortgage(){
        double mortgageRaw = calc.calculateMortgage();
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("----------");
        System.out.print("Monthly Payments: " + currencyFormatter.format(mortgageRaw));
        System.out.println();
    }
}
