package payroll.employee;

import payroll.payment.PaymentMethod;
import payroll.syndicate.Syndicate;

public class Employee {
    private int id;
    private String name;
    private String address;
    private PaymentMethod paymentMethod;
    private Syndicate sindicalist;

    public Employee() {}

    public Employee(int id, String name, String address, PaymentMethod paymentMethod, Syndicate sindicalist) {
        setId(id);
        setName(name);
        setAddress(address);
        setPaymentMethod(paymentMethod);
        setSyndicate(sindicalist);
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
        String message = "Employee ID: " + getId() + "\nName: " + getName() + "\nAddress: " + getAddress() + "\nPayment method: " ;
        if (getSyndicate() != null) {
            message += getSyndicate().toString();
        } else {
            message += "\nDoes not belong to syndicate.";
        }
        return message;
    }
}
