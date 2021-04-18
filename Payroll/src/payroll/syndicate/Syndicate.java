package payroll.syndicate;

import java.util.ArrayList;
import java.util.List;

public class Syndicate {
    private int idSyndicate;
    private Double tax;
    private List<AdditionalFee> additionalFee;
    private boolean active;

    public Syndicate(int idSyndicate, Double tax, boolean active) {
        this.idSyndicate = idSyndicate;
        this.tax = tax;
        this.active = active;
        this.additionalFee = new ArrayList<AdditionalFee>();
    }

    public int getIdSyndicate() {
        return idSyndicate;
    }

    public void setIdSyndicate(int idSyndicate) {
        this.idSyndicate = idSyndicate;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<AdditionalFee> getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(List<AdditionalFee> additionalFee) {
        this.additionalFee = additionalFee;
    }

    @Override
    public String toString() {
        return "\n--Syndicate info: --\nSyndicate ID: " + getIdSyndicate() + "\nSyndicate Tax: "
                + getTax();
    }
}
