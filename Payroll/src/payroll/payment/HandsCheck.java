package payroll.payment;

public class HandsCheck extends PaymentMethod{
    private int numberCheck;

    public HandsCheck(String bankId, String agency, String accountNumber, Double value, int numberCheck) {
        super(bankId, agency, accountNumber, value);
        this.numberCheck = numberCheck;
    }

    public int getNumberCheck() {
        return numberCheck;
    }

    public void setNumberCheck(int numberCheck) {
        this.numberCheck = numberCheck;
    }

    @Override
    public String toString() {
        return super.toString() +"\nType of Payment: "+ "Hands Check\n" + "Number Check: " + getNumberCheck();
    }
}
