package payroll.payment;

public class CheckByPostOffice extends PaymentMethod{
    private int numberCheck;
    private String address;

    public CheckByPostOffice(String bankId, String agency, String accountNumber, Double value, int numberCheck, String address) {
        super(bankId, agency, accountNumber, value);
        this.numberCheck = numberCheck;
        this.address = address;
    }

    public int getNumberCheck() {
        return numberCheck;
    }

    public void setNumberCheck(int numberCheck) {
        this.numberCheck = numberCheck;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType of Payment: " + "Check by Post Office\n" + "Number Check: " + getNumberCheck()+ "\nAddress: " + getAddress();
    }

}
