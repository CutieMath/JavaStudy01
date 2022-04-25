import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        // Take input
        String principlePrompt = "Principle ($1K - $1M): ";
        String interestRatePrompt = "Annual Interest Rate (1 - 30): ";
        String periodPrompt = "Period (Years, 1- 30):  ";
        int principle = (int)readNumber(principlePrompt, 1000, 1_000_000_000);
        float interestRate = (float)readNumber(interestRatePrompt, 1, 30);
        short period = (short)readNumber(periodPrompt, 1, 30);

        // Calculate the result
        double mortgageRaw = calculateMortgage(principle, interestRate, period);

        // Format the result and print
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.print("Your monthly payment is: " + formatter.format(mortgageRaw));
    }

    public static double calculateMortgage(
            int principle,
            float interestRate,
            short period) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        interestRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
        period *= MONTHS_IN_YEAR;
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
}
