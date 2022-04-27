import java.text.NumberFormat;

public class Mortgage {

    private int principle;
    private float interestRate;
    private short period;

    // Constructor
    public Mortgage(int principle, float interestRate, short period){
        setPrinciple(principle);
        setInterestRate(interestRate);
        setPeriod(period);
    }

    // Setters
    private void setPrinciple(int principle){
        this.principle = principle;
    }
    private void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }
    private void setPeriod(short period){
        this.period = period;
    }

    // Public methods
    public void printMortgage(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double mortgageRaw = calculateMortgage();
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("----------");
        System.out.print("Monthly Payments: " + formatter.format(mortgageRaw));
        System.out.println();
    }

    public void printRemainingMortgage(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.println();
        System.out.println("REMAINING BALANCE");
        System.out.println("-------------------");
        for(int i = 1; i <= period; i ++){
            double balance = calculateBalance(i);
            System.out.println(formatter.format(balance));
        }
    }

    // Private methods
    private double calculateMortgage() {
        double mortgageRaw = principle * ((interestRate * Math.pow((1 + interestRate), period)) / (Math.pow((1 + interestRate), period) - 1));
        return mortgageRaw;
    }
    private double calculateBalance(int i) {
        double balance = principle * ((Math.pow((1 + interestRate), period) - Math.pow((1 + interestRate), i)) / ( Math.pow((1 + interestRate), period) - 1));
        return balance;
    }


}
