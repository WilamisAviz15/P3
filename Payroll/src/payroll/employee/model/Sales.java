package payroll.employee.model;

import java.time.LocalDate;

public class Sales {
    private LocalDate date;
    private Double value;

    public Sales(LocalDate date, Double value){
        this.date = date;
        this.value = value;
    }
    public LocalDate getDate() {
        return date;
    }

    public Sales(Sales s){
        this(s.getDate(), s.getValue());
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
        return "Date: " + getDate() +" | "+ "Value: " + getValue();
    }
}
