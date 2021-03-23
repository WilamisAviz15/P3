package payroll;

public class Employee {
    private int id;
    private String name;
    private String address;
    private int type_employee; // (0 - hourly, 1 - salaried, 2 - commissioned)
    private Double salary;
    private int comissioned; // (1 - yes, 2 - no)
    private int payment_method; // (0 - Check by the post office, 1 - Check in Person, 2- Bank Account)
    private int sindicalist; // (1 - yes, 2 - no)

    public Employee(int id, String name, String address, int type_employee, Double salary,
            int payment_method, int sindicalist) {
        setId(id);
        setName(name);
        setAddress(address);
        setType_employee(type_employee);
        setSalary(salary);
        setComissioned(comissioned);
        setPayment_method(payment_method);
        setSindicalist(sindicalist);
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

    @Override
    public String toString() {
        String type_emp = "";
        String paymentString = "";
        String SindicalistString = "";

        if (this.getType_employee() == 0) {
            type_emp = "Hourly";
        } else if (this.getType_employee() == 1) {
            type_emp = "Assalaried";
        } else if (this.getType_employee() == 2) {
            type_emp = "Comissioned";
        }

        // 0 - Check by the post office, 1 - Check in Person, 2 - Bank Account
        if (this.getPayment_method() == 0) {
            paymentString = "Check by the post office";
        } else if (this.getPayment_method() == 1) {
            paymentString = "Check in Person";
        } else if (this.getPayment_method() == 2) {
            paymentString = "Bank Account";
        }

        if (this.getSindicalist() == 1) {
            SindicalistString = "Yes";
        } else if (this.getSindicalist() == 2) {
            SindicalistString = "No";
        }

        return String.format(
                "\nID: %d\nName: %s\nAddress: %s\nType Employee: %s\nSalary: %.2f\nPayment Method: %s\nSindicalist: %s\n",
                this.getId(), this.getName(), this.getAddress(), type_emp, this.getSalary(), paymentString,
                SindicalistString);
    }
}
