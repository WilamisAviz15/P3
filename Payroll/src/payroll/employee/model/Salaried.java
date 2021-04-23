package payroll.employee.model;

import payroll.payment.model.PaymentMethod;
import payroll.syndicate.Syndicate;

public class Salaried extends Employee {
    private Double salary;

    public Salaried(){}

    public Salaried(Salaried s) {
        this(s.getId(), s.getName(), s.getAddress(), s.getPaymentMethod(), s.getSalary(), s.getSyndicate());
    }

    public Salaried(int id, String name, String address, PaymentMethod paymentMethod, Double salary,
            Syndicate sindicalist) {
        super(id, name, address, paymentMethod, sindicalist);
        this.salary = salary;
    }

    public Salaried(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "\nType of Employee: Salaried\n" + "salary: " + getSalary().toString() + super.toString();
    }
}
