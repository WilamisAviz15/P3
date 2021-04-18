package payroll.employee;

public class Sales {
    private String date;
    private Double value;

    public Sales(String date, Double value){
        this.date = date;
        this.value = value;
    }
    public String getDate() {
        return date;
    }

    public void setData(String date) {
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
