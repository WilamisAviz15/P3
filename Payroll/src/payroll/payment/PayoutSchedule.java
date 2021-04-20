package payroll.payment;

import java.util.ArrayList;
import java.util.List;

public class PayoutSchedule {
    
    private List<String> opDefault;

    public PayoutSchedule() {
        this.opDefault = new ArrayList<String>();
        this.opDefault.add("weekly");
        this.opDefault.add("monthly");
        this.opDefault.add("biweekly");
    }


    public List<String> getopDefault() {
        return this.opDefault;
    }

    public void setopDefault(List<String> opDefault) {
        this.opDefault = opDefault;
    }

    
    public void addOption(String newOp) {
        this.opDefault.add(newOp);
    }
    }
