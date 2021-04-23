package payroll.payment.model;

public class PaymentMethod {
    private String bankId;
    private String agency;
    private String accountNumber;
    private String paySchedule;

    public PaymentMethod() {
    }

    public PaymentMethod(PaymentMethod pM){
        this(pM.getBankId(), pM.getAgency(), pM.getAccountNumber(), pM.getPaySchedule());
    }
    
    public PaymentMethod(String bankId, String agency, String accountNumber, String paySchedule) {
        this.bankId = bankId;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.paySchedule = paySchedule;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPaySchedule() {
        return paySchedule;
    }

    public void setPaySchedule(String paySchedule) {
        this.paySchedule = paySchedule;
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
        return "BankId: " + getBankId() + "\nAgency: " + getAgency() + "\nAccount Number: " + getAccountNumber() + "\nPayment Schedule: " + getPaySchedule();
    }
}
