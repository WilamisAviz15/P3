package payroll.employee;

import payroll.payment.PaymentMethod;
import payroll.syndicate.Syndicate;

public class Salaried extends Employee {
    private Double salary;

    public Salaried(int id, String name, String address, PaymentMethod paymentMethod, Double salary, Syndicate sindicalist) {
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
        return super.toString() + "\n==== Salaried: ====\n" + "salary: " + getSalary().toString();
    }
}
