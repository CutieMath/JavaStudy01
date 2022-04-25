import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args){
        // Take input
        String principlePrompt = "Principle ($1K - $1M): ";
        String interestRatePrompt = "Annual Interest Rate (1 - 30): ";
        String periodPrompt = "Period (Years, 1- 30):  ";
        int principle = (int)readNumber(principlePrompt, 1000, 1_000_000_000);
        float interestRate = (float)readNumber(interestRatePrompt, 1, 30);
        short period = (short)readNumber(periodPrompt, 1, 30);

        // Optimised the input
        interestRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
        period *= MONTHS_IN_YEAR;

        // Format the result and print
        printMortgage(principle, interestRate, period);
        printRemainingMortgage(principle, interestRate, period);
    }
    public static double calculateMortgage(int principle, float interestRate, short period) {
        double mortgageRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));
        return mortgageRaw;
    }
    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        System.out.print(prompt);
        value = scanner.nextDouble();
        while(value < min || value > max){
            System.out.println("Please enter value between " + min + " and " + max + ".");
            System.out.print(prompt);
            value = scanner.nextDouble();
        }
        return value;
    }
    public static void printMortgage(int principle, float interestRate, short period){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double mortgageRaw = calculateMortgage(principle, interestRate, period);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("----------");
        System.out.print("Monthly Payments: " + formatter.format(mortgageRaw));
        System.out.println();
    }
    public static void printRemainingMortgage(int principle, float interestRate, short period){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.println();
        System.out.println("REMAINING BALANCE");
        System.out.println("-------------------");
        for(int i = 1; i <= period; i ++){
            double balance = principle * ((Math.pow((1 + interestRate), period) - Math.pow((1 + interestRate), i)) / ( Math.pow((1 + interestRate), period) - 1));
            System.out.println(formatter.format(balance));
        }
    }
}
