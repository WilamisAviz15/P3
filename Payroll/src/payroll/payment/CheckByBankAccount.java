package payroll.payment;

public class CheckByBankAccount extends PaymentMethod{
    private String accountType;

    public CheckByBankAccount(String bankId, String agency, String accountNumber, Double value, String accountType) {
        super(bankId, agency, accountNumber, value);
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return super.toString() +"\nType of Payment: " + "Check By Bank Account\n" + "Account Type: " + getAccountType();
    }
}
