package payroll.payment.model;

import java.util.ArrayList;
import java.util.List;

public class PaymentSchedule{

    private List<String> typesSchedule;

    public PaymentSchedule() {
        this.typesSchedule = new ArrayList<String>();
        this.typesSchedule.add("weekly");
        this.typesSchedule.add("monthly");
        this.typesSchedule.add("biweekly");
    }

    public List<String> getTypesSchedule() {
        return this.typesSchedule;
    }

    public void setTypesSchedule(String typesSchedule) {
        this.typesSchedule.add(typesSchedule);
    }

    public void print(){
        for (String pS : typesSchedule) {
            System.out.println(" - " + pS);
        }
    }
}
