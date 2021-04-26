package payroll.employee.model;

import java.util.ArrayList;
import java.util.List;

import payroll.payment.model.PaymentMethod;
import payroll.payment.model.Payslip;
import payroll.syndicate.Syndicate;

public class Hourly extends Employee {
    private Double hourlyValue;
    private List<Timecard> timeCard;

    public Hourly() {
    }

    public Hourly(Hourly s) {
        this(s.getId(), s.getName(), s.getAddress(), s.getPaymentMethod(), s.getHourlyValue(), s.getSyndicate(), s.getPayslipSheet());
        this.setTimecard(s.getTimecard());
    }

    public Hourly(int id, String name, String address, PaymentMethod paymentMethod, Double hourlyValue,
            Syndicate sindicalist, List<Payslip> payslipSheet) {
        super(id, name, address, paymentMethod, sindicalist, payslipSheet);
        this.hourlyValue = hourlyValue;
        this.timeCard = new ArrayList<Timecard>();
    }

    public Hourly(Double hourlyValue) {
        this.hourlyValue = hourlyValue;
        this.timeCard = new ArrayList<Timecard>();
    }

    public Double getHourlyValue() {
        return hourlyValue;
    }

    public void setHourlyValue(Double hourlyValue) {
        this.hourlyValue = hourlyValue;
    }

    public List<Timecard> getTimecard() {
        return this.timeCard;
    }

    public void setTimecard(List<Timecard> timeCard) {
        this.timeCard = timeCard;
    }

    @Override
    public String toString() {
        return "\nType of Employee:" + getClass().getSimpleName() + "\nValue by hour: " + getHourlyValue().toString()
                + "\nTimecard: " + getTimecard().toString() + super.toString();
    }

}
