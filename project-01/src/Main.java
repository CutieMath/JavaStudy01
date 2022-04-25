import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        // Take input
        int principle = 0;
        System.out.print("Principle ($1K - $1M): ");
        principle = scanner.nextInt();
        while(principle < 1000 || principle > 1_000_000_000){
            System.out.println("Please enter value between $1K - $1M.");
            System.out.print("Principle ($1K - $1M): ");
            principle = scanner.nextInt();
        }

        float interestRate = (float)0.00;
        System.out.print("Annual Interest Rate (0 - 30): ");
        interestRate = scanner.nextFloat();
        while(interestRate <= 0 || interestRate > 30) {
            System.out.println("Please enter value between 0 - 30.");
            System.out.print("Annual Interest Rate (0 - 30): ");
            interestRate = scanner.nextFloat();
        }

        short period = 0;
        System.out.print("Period (Years, 1 - 30): ");
        period = scanner.nextShort();
        while(period < 1 || period > 30){
            System.out.println("Please enter value between 1 - 30.");
            System.out.print("Period (Years, 1- 30): ");
            period = scanner.nextShort();
        }

        // Calculate the result
        interestRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
        period *= 12;
        double resRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));

        // Format the result and print
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.print("Your monthly payment is: " + formatter.format(resRaw));
    }
}
