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

        // Create com.cutiemath.Mortgage instance
        Mortgage mortgage = new Mortgage(principle, interestRate, period);
        mortgage.printMortgage();
        mortgage.printRemainingMortgage();
    }
}
