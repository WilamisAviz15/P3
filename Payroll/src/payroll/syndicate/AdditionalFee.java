package payroll.syndicate;

public class AdditionalFee {
    private String date;
    private Double value;

    public AdditionalFee(String date, Double value){
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
        return "Date: " + getDate() +" | "+ "Value: " + getValue()+"\n";
    }
}
