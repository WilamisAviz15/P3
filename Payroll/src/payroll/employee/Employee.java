package payroll.employee;

import payroll.Timecard;

public class Employee {
    private int id;
    private String name;
    private String address;
    private Timecard timeCard;

    public Employee() {
    }

    public Employee(int id, String name, String address) {
        setId(id);
        setName(name);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String type_emp = "";
        String paymentString = "";
        String SindicalistString = "";

        return String.format(
                "\nID: %d\nName: %s\nAddress: %s\nType Employee: %s\nPayment Method: %s\nSindicalist: %s\n",
                this.getId(), this.getName(), this.getAddress(), type_emp, paymentString,
                SindicalistString);
    }
}
