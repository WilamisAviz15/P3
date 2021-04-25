package payroll.payment.model;

import java.time.LocalDate;

public class Payslip {
    private Double basicPay = 0.00; // bruto
    private Double netPay = 0.00; // liquido
    private Double hours = 0.00;
    private Double extrasHours = 0.00;
    private LocalDate date;
    private Double commissionsValue = 0.00;
    private Double tax = 0.00;
    private Double additionaTax = 0.00;
    private String referenceMonth;
    private Boolean lastPaidIsHoliday;
    private int countTimecard;

    public Payslip(Double basicPay, Double netPay, LocalDate date, Double tax, Double additionaTax,
            String referenceMonth) {
        this.basicPay = basicPay;
        this.netPay = netPay;
        this.date = date;
        this.tax = tax;
        this.additionaTax = additionaTax;
        this.referenceMonth = referenceMonth;
    }

    public Payslip(Double basicPay, Double netPay, LocalDate date, Double tax, Double additionaTax,
            String referenceMonth, Double commissionsValue) {
        this.basicPay = basicPay;
        this.netPay = netPay;
        this.date = date;
        this.tax = tax;
        this.additionaTax = additionaTax;
        this.referenceMonth = referenceMonth;
        this.commissionsValue = commissionsValue;
    }

    public Payslip(Double basicPay, Double netPay, Double hours, Double extrasHours, LocalDate date, Double tax,
            Double additionaTax, String referenceMonth, int countTimecard, boolean lastPaidIsHoliday) {
        this.basicPay = basicPay;
        this.netPay = netPay;
        this.hours = hours;
        this.extrasHours = extrasHours;
        this.date = date;
        this.tax = tax;
        this.additionaTax = additionaTax;
        this.referenceMonth = referenceMonth;
        this.countTimecard = countTimecard;
        this.lastPaidIsHoliday = lastPaidIsHoliday;
    }

    public Double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(Double basicPay) {
        this.basicPay = basicPay;
    }

    public Boolean getLastPaidIsHoliday() {
        return lastPaidIsHoliday;
    }

    public void setLastPaidIsHoliday(Boolean lastPaidIsHoliday) {
        this.lastPaidIsHoliday = lastPaidIsHoliday;
    }

    public int getCountTimecard() {
        return countTimecard;
    }

    public void setCountTimecard(int countTimecard) {
        this.countTimecard = countTimecard;
    }

    public String getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(String referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public Double getNetPay() {
        return netPay;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getExtrasHours() {
        return extrasHours;
    }

    public void setExtrasHours(Double extrasHours) {
        this.extrasHours = extrasHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCommissionsValue() {
        return commissionsValue;
    }

    public void setCommissionsValue(Double commissionsValue) {
        this.commissionsValue = commissionsValue;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAdditionaTax() {
        return additionaTax;
    }

    public void setAdditionaTax(Double additionaTax) {
        this.additionaTax = additionaTax;
    }
}
