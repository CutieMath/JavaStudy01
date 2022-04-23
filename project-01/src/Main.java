import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        try{

            System.out.print("Principle: ");
            Scanner scanner1 = new Scanner(System.in);
            int principle = scanner1.nextInt();

            System.out.print("Annual Interest Rate: ");
            Scanner scanner2 = new Scanner(System.in);
            float interestRate = scanner2.nextFloat();

            System.out.print("Period (Years): ");
            Scanner scanner3 = new Scanner(System.in);
            short period = scanner3.nextShort();

            // Calculate the result
            interestRate = (interestRate / 100) / 12;
            period *= 12;
            double resRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));
            // Format the result
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            System.out.print(formatter.format(resRaw));

        } catch(Exception e) {
            System.out.println("Error occurred:" + e.toString());
        }
    }
}
