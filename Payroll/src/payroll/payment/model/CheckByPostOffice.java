package payroll.payment.model;

public class CheckByPostOffice extends PaymentMethod{
    private int numberCheck;
    private String address;

    public CheckByPostOffice(String bankId, String agency, String accountNumber, String paySchedule, int numberCheck, String address) {
        super(bankId, agency, accountNumber,paySchedule);
        this.numberCheck = numberCheck;
        this.address = address;
    }

    public CheckByPostOffice(CheckByPostOffice hC){
        this(hC.getBankId(), hC.getAgency(), hC.getAccountNumber(), hC.getPaySchedule(), hC.getNumberCheck(), hC.getAddress());
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
