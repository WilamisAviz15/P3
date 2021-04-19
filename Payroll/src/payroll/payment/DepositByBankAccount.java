package payroll.payment;

public class DepositByBankAccount extends PaymentMethod{
    private String accountType;

    public DepositByBankAccount(String bankId, String agency, String accountNumber, String accountType) {
        super(bankId, agency, accountNumber);
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
        return super.toString() +"\nType of Payment: " + "Deposit By Bank Account\n" + "Account Type: " + getAccountType();
    }
}
