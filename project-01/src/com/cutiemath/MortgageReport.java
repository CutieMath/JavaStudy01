package com.cutiemath;

import java.text.NumberFormat;

public class MortgageReport {
    private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private MortgageCalculator calc;

    public MortgageReport(MortgageCalculator calc) {
        this.calc = calc;
    }
    public void printRemainingMortgage(){
        System.out.println();
        System.out.println("REMAINING BALANCE");
        System.out.println("-------------------");
        for (double balance : calc.getRemainingBalances()){
            System.out.println(formatter.format(balance));
        }
    }
    public void printMortgage(){
        double mortgageRaw = calc.calculateMortgage();
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("----------");
        System.out.print("Monthly Payments: " + formatter.format(mortgageRaw));
        System.out.println();
    }
}
