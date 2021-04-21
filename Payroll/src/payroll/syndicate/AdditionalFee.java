package payroll.syndicate;

import java.time.LocalDate;

public class AdditionalFee {
    private LocalDate date;
    private Double value;

    public AdditionalFee(LocalDate date, Double value){
        this.date = date;
        this.value = value;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setData(LocalDate date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setData(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Date: " + getDate() +" | "+ "Value: " + getValue()+"\n";
    }
}
