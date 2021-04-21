package payroll.payment.model;

public class HandsCheck extends PaymentMethod{
    private int numberCheck;

    public HandsCheck(String bankId, String agency, String accountNumber,String paySchedule, int numberCheck) {
        super(bankId, agency, accountNumber, paySchedule);
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
