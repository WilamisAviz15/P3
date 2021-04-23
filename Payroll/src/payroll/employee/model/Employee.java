package payroll.employee.model;

import payroll.payment.model.PaymentMethod;
import payroll.syndicate.Syndicate;

public class Employee {
    private int id;
    private String name;
    private String address;
    private PaymentMethod paymentMethod;
    private Syndicate sindicalist;

    public Employee() {
    }

    public Employee(Employee s) {
        this(s.getId(), s.getName(), s.getAddress(), s.getPaymentMethod(), s.getSyndicate());
    }

    public Employee(int id, String name, String address, PaymentMethod paymentMethod, Syndicate sindicalist) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.sindicalist = sindicalist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Syndicate getSyndicate() {
        return this.sindicalist;
    }

    public void setSyndicate(Syndicate sindicalist) {
        this.sindicalist = sindicalist;
    }

    @Override
    public String toString() {
        String message = "\nEmployee ID: " + getId() + "\nName: " + getName() + "\nAddress: " + getAddress()
                + "\n--Info Payment-- \n" + getPaymentMethod();
        if (getSyndicate().getActive() == true) {
            message += getSyndicate().toString();
        } else {
            message += "\n--Syndicate info--\n Does not belong to syndicate.";
        }
        return message;
    }
}
