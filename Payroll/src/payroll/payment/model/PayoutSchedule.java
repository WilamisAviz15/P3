package payroll.payment.model;

import java.util.ArrayList;
import java.util.List;

public class PayoutSchedule {

    private List<String> typesSchedule;

    public PayoutSchedule() {
        this.typesSchedule = new ArrayList<String>();
        this.typesSchedule.add("weekly");
        this.typesSchedule.add("monthly");
        this.typesSchedule.add("biweekly");
    }

    public List<String> getTypesSchedule() {
        return this.typesSchedule;
    }

    public void setTypesSchedule(List<String> typesSchedule) {
        this.typesSchedule = typesSchedule;
    }
}
