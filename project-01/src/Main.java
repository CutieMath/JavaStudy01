import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

            final byte MONTHS_IN_YEAR = 12;
            final byte PERCENT = 100;

        try{
            Scanner scanner = new Scanner(System.in);

            // Take input
            System.out.print("Principle: ");
            int principle = scanner.nextInt();
            System.out.print("Annual Interest Rate: ");
            float interestRate = scanner.nextFloat();
            System.out.print("Period (Years): ");
            short period = scanner.nextShort();

            // Calculate the result
            interestRate = (interestRate / PERCENT) / MONTHS_IN_YEAR;
            period *= 12;
            double resRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));

            // Format the result and print
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            System.out.print("Your monthly payment is: " + formatter.format(resRaw));

        } catch(Exception e) {
            System.out.println("Error occurred:" + e.toString());
        }
    }
}
