package payroll;

public class Employee {
    private int id;
    private String name;
    private String address;
    private int type_employee; //(0 - hourly, 1 - salaried, 2 - commissioned)
    private Double salary;
    private int comissioned; //(1 - yes, 2 - no)
    private int payment_method; //(0 - Check by the post office, 1 - Check in Person, 2- Bank Account)
    private int sindicalist; //(1 - yes, 2 - no)

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

    public int getType_employee() {
        return type_employee;
    }

    public void setType_employee(int type_employee) {
        this.type_employee = type_employee;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getComissioned() {
        return comissioned;
    }

    public void setComissioned(int comissioned) {
        this.comissioned = comissioned;
    }

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public int getSindicalist() {
        return sindicalist;
    }

    public void setSindicalist(int sindicalist) {
        this.sindicalist = sindicalist;
    }

    public void listAllEmployee(String id){
        
    }
}
