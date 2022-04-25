import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int principle = 0;
        float interestRate = (float)0.00;
        byte period = 0;

        // Take input
        System.out.print("Principle ($1K - $1M): ");
        principle = scanner.nextInt();
        while(principle < 1000 || principle > 1_000_000_000){
            System.out.println("Please enter value between $1K - $1M.");
            System.out.print("Principle ($1K - $1M): ");
            principle = scanner.nextInt();
        }

        System.out.print("Annual Interest Rate (0 - 30): ");
        interestRate = scanner.nextFloat();
        while(interestRate <= 0 || interestRate > 30) {
            System.out.println("Please enter value between 0 - 30.");
            System.out.print("Annual Interest Rate (0 - 30): ");
            interestRate = scanner.nextFloat();
        }

        System.out.print("Period (Years, 1 - 30): ");
        period = scanner.nextByte();
        while(period < 1 || period > 30){
            System.out.println("Please enter value between 1 - 30.");
            System.out.print("Period (Years, 1- 30): ");
            period = scanner.nextByte();
        }

        // Calculate the result
        double mortgageRaw = calculateMortgage(principle, interestRate, period);

        // Format the result and print
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.print("Your monthly payment is: " + formatter.format(mortgageRaw));
    }

    public static double calculateMortgage(
            int principle,
            float interestRate,
            byte period) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        interestRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
        period *= MONTHS_IN_YEAR;
        double mortgageRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));
        return mortgageRaw;
    }
}
