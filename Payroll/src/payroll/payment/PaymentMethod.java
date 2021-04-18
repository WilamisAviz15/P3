package payroll.payment;

import payroll.employee.Employee;

public class PaymentMethod {
    private String bankId;
    private String agency;
    private String accountNumber;
    private Double value;

    public PaymentMethod() {
    }

    public PaymentMethod(String bankId, String agency, String accountNumber, Double value) {
        this.bankId = bankId;
        this.agency = agency;
        this.accountNumber = accountNumber;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankId: " + getBankId() + "\nAgency: " + getAgency() + "\nAccount Number: " + getAccountNumber();
    }
}
