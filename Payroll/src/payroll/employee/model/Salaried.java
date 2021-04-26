package payroll.employee.model;

import java.util.List;

import payroll.payment.model.PaymentMethod;
import payroll.payment.model.Payslip;
import payroll.syndicate.Syndicate;

public class Salaried extends Employee {
    private Double salary;

    public Salaried() {
    }

    public Salaried(Salaried s) {
        this(s.getId(), s.getName(), s.getAddress(), s.getPaymentMethod(), s.getSalary(), s.getSyndicate(), s.getPayslipSheet());
    }

    public Salaried(int id, String name, String address, PaymentMethod paymentMethod, Double salary,
            Syndicate sindicalist, List<Payslip> payslipSheet) {
        super(id, name, address, paymentMethod, sindicalist, payslipSheet);
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
        return "\nType of Employee:" + getClass().getSimpleName() + "\nsalary: " + getSalary().toString()
                + super.toString();
    }
}
