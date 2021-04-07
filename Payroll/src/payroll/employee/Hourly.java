package payroll.employee;

import java.util.ArrayList;
import java.util.List;
import payroll.employee.Timecard;
import payroll.payment.PaymentMethod;
import payroll.syndicate.Syndicate;

public class Hourly extends Employee {
    private Double hourlyValue;
    private List<Timecard> timeCard;

    public Hourly(int id, String name, String address, PaymentMethod paymentMethod, Double hourlyValue,
            Syndicate sindicalist) {
        super(id, name, address, paymentMethod, sindicalist);
        this.hourlyValue = hourlyValue;
        this.timeCard = new ArrayList<Timecard>();
    }

    public Hourly(Double hourlyValue) {
        this.hourlyValue = hourlyValue;
        this.timeCard = new ArrayList<Timecard>();
    }

    public Double gethourlyValue() {
        return hourlyValue;
    }

    public void sethourlyValue(Double hourlyValue) {
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
        return super.toString() + "\n==== Hourly: ====\n" + "Value by hour: " + gethourlyValue().toString()
                + "\nTimecard: " + getTimecard().toString();
    }
}
