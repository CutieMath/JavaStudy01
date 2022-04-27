package com.cutiemath;

public class Main {
    public static void main(String[] args){
        // Take input
        String principlePrompt = "Principle ($1K - $1M): ";
        String interestRatePrompt = "Annual Interest Rate (1 - 30): ";
        String periodPrompt = "Period (Years, 1- 30):  ";
        int principle = (int) Console.readNumber(principlePrompt, 1000, 1_000_000_000);
        float interestRate = (float) Console.readNumber(interestRatePrompt, 1, 30);
        short period = (short) Console.readNumber(periodPrompt, 1, 30);

        MortgageCalculator calc = new MortgageCalculator(principle, interestRate, period);
        MortgageReport report = new MortgageReport(calc);
        report.printMortgage();
        report.printRemainingMortgage();
    }
}
