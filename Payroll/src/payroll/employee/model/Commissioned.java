package payroll.employee.model;

import java.util.ArrayList;
import java.util.List;

import payroll.payment.PaymentMethod;
import payroll.syndicate.Syndicate;

public class Commissioned extends Employee {
    private Double salary;
    private Double comission;
    private List<Sales> sales;

    public Commissioned(Double salary, Double comission) {
        this.salary = salary;
        this.comission = comission;
        this.sales = new ArrayList<Sales>();
    }

    public Commissioned(int id, String name, String address, PaymentMethod paymentMethod, Double salary,
            Double comission, Syndicate sindicalist) {
        super(id, name, address, paymentMethod, sindicalist);
        this.salary = salary;
        this.comission = comission;
        this.sales = new ArrayList<Sales>();
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }

    public List<Sales> getSales() {
        return this.sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "\nType of Employee: Commissioned\n" + "Salary: " + getSalary().toString()
                + "\nComission (%): " + getComission().toString() + "\nSales: " + getSales().toString() +super.toString();
    }
}
