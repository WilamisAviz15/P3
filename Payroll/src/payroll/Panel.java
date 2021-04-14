package payroll;

import java.util.Scanner;

import payroll.employee.Commissioned;
import payroll.employee.Employee;
import payroll.employee.Hourly;
import payroll.employee.Salaried;
import payroll.payment.CheckByBankAccount;
import payroll.payment.CheckByPostOffice;
import payroll.payment.HandsCheck;
import payroll.payment.PaymentMethod;
import payroll.syndicate.Syndicate;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    public int id = 20210000;
    public int idSyndicate = 0000;
    public int size = 0; // current size employee array
    public int size_timecard = 0; // current size timecard array
    public List<Employee> list_employee = new ArrayList<Employee>();

    public void Employees() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Employees ---");
            System.out.println("1 - Add Employees");
            System.out.println("2 - Remove Employees");
            System.out.println("3 - Edit Employees");
            System.out.println("4 - List All Employees");
            System.out.println("5 - Search Employees");
            System.out.println("6 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                addEmployee();
                break;
            case 2:
                removeEmployee();
                break;
            case 3:
                editEmployee();
                break;
            case 4:
                ListAllEmployee();
                break;
            case 5:
                listEmployeeById();
                break;
            }

        } while (option != 6);
    }

    public void addEmployee() {
        PaymentMethod paymentMethod = null;
        Syndicate sindicalist = null;
        Employee newEmployees;
        String bankId;
        String agency;
        String accountNumber;
        Double value;
        Scanner sc = new Scanner(System.in);
        int optionPaymentMethod, optionSindicalist;
        Double salary = 0.00;
        System.out.println("Type the name:");
        String name = sc.nextLine();
        System.out.println("Type the adrress:");
        String address = sc.nextLine();
        System.out.println("Type of employee (0 - hourly, 1 - salaried, 2 - commissioned)");
        int type_employee = sc.nextInt();
        if (type_employee == 0) {
            System.out.println("Type the hourly wage:");
            salary = sc.nextDouble();
            newEmployees = new Hourly(++id, name, address, paymentMethod, salary, sindicalist);
        } else if (type_employee == 1) {
            System.out.println("Type the salary:");
            salary = sc.nextDouble();
            newEmployees = new Salaried(++id, name, address, paymentMethod, salary, sindicalist);
        } else if (type_employee == 2) {
            double percentage;
            System.out.println("Type the salary:");
            salary = sc.nextDouble();
            System.out.println("Type the % to comisson:");
            percentage = sc.nextDouble();
            newEmployees = new Commissioned(++id, name, address, paymentMethod, salary, percentage, sindicalist);
        } else {
            newEmployees = new Employee(++id, name, address, paymentMethod, sindicalist);
        }
        System.out.println("Type Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
        optionPaymentMethod = sc.nextInt();
        sc.nextLine();
        System.out.println("Type the bank ID:");
        bankId = sc.nextLine();
        System.out.println("Type agency number:");
        agency = sc.nextLine();
        System.out.println("Type account number:");
        accountNumber = sc.nextLine();
        if (optionPaymentMethod == 0) {
            int numberCheck;
            System.out.println("Type number Check:");
            numberCheck = sc.nextInt();
            paymentMethod = new CheckByPostOffice(bankId, agency, accountNumber, salary, numberCheck, address);
        } else if (optionPaymentMethod == 1) {
            int numberCheck;
            System.out.println("Type number Check:");
            numberCheck = sc.nextInt();
            paymentMethod = new HandsCheck(bankId, agency, accountNumber, salary, numberCheck);
        } else if (optionPaymentMethod == 2) {
            
        }
        newEmployees.setPaymentMethod(paymentMethod);

        System.out.printf("%s will be part of the Syndicate? 1- yes | 2- no\n", newEmployees.getName());
        optionSindicalist = sc.nextInt();

        if (optionSindicalist == 1) {
            Double tax;
            System.out.println("Type the monthly TAX:");
            tax = sc.nextDouble();
            sindicalist = new Syndicate(++idSyndicate, tax);
            newEmployees.setSyndicate(sindicalist);
        }
        list_employee.add(newEmployees);
        System.out.println("Successful registration.");
        
    }

    public int findEmployee() {
        int id;
        int aux = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        id = sc.nextInt();
        for (Employee listE : list_employee) {
            if (listE.getId() == id) {
                return aux;
            }
            aux++;
        }
        return -1;
    }

    public void removeEmployee() {
        int index = findEmployee();
        if (index != -1) {
            list_employee.remove(index);
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void LaunchSales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();

        System.out.println("Employee not found.");

    }

    public void ListSales() {
    }

    public void ListAllEmployee() {
        System.out.println("___________________________________________");
        for (Employee listE : list_employee) {
            System.out.println(listE);
            System.out.println("___________________________________________");
        }
        
    }

    public void listEmployeeById() {
        int id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        id = sc.nextInt();
        System.out.println("___________________________");
        for (Employee listE : list_employee) {
            if (listE.getId() == id) {
                System.out.println(listE);
            }
        }
        System.out.println("___________________________");
    }

    public void editEmployee() {
        String id, op, attr;
        Scanner sc = new Scanner(System.in);
        int index = findEmployee();
        if (index != -1) {
            System.out.printf("Employed selected: %s. What do you want to edit?\n", list_employee.get(index).getName());
            System.out.println("0 - Name");
            System.out.println("1 - Address");
            System.out.println("2 - Type of employee");
            System.out.println("3 - Paymento Method");
            op = sc.nextLine();
            if (op.equals("0")) {
                System.out.println("Type the new name");
                attr = sc.nextLine();
                list_employee.get(index).setName(attr);
            } else if (op.equals("1")) {
                System.out.println("Type the new address");
                attr = sc.nextLine();
                list_employee.get(index).setAddress(attr);
            } else if (op.equals("2")) {
                // int oldAttr = list_employee.get(index).getType_employee();
                System.out.println("Type the new type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
                attr = sc.nextLine();
                // list_employee.get(index).setType_employee(Integer.parseInt(attr));
                if (attr.equals("0")) {
                    System.out.println("Type the hourly wage:");
                    attr = sc.nextLine();
                    // list_employee.get(index).setSalary(Double.parseDouble(attr));

                } else if (attr.equals("1")) {
                    System.out.println("Type the salary:");
                    attr = sc.nextLine();
                    // list_employee.get(index).setSalary(Double.parseDouble(attr));

                } else if (attr.equals("2")) {
                    System.out.println("Type the salary:");
                    attr = sc.nextLine();
                    // list_employee.get(index).setSalary(Double.parseDouble(attr));
                    // list_employee.get(index).setComissioned(1);
                }
            } else if (op.equals("3")) {
                System.out.println(
                        "Select the new Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
                attr = sc.nextLine();
                // list_employee.get(index).setPayment_method(Integer.parseInt(attr));
            }
            System.out.println("Successful changes.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void Login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();

    }

    public void Logout() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();

    }

    public void ListTimecard() {

    }

    public void LaunchFee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();

    }

    public void ListFee() {

    }

    public void Timecard() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("Timecard");
            System.out.println("1 - Timecard login");
            System.out.println("2 - Timecard logout");
            System.out.println("3 - List All Timecards");
            System.out.println("4 - Search Timecards by ID");
            System.out.println("5 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                Login();
                break;
            case 2:
                Logout();
                break;
            case 3:
                ListTimecard();
                break;
            case 4:
                break;
            }
        } while (option != 5);
    }

    public void Sales() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("Sales");
            System.out.println("1 - Launch Sale");
            System.out.println("2 - List All Sales");
            System.out.println("3 - Search Sales by ID");
            System.out.println("4 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                LaunchSales();
                break;
            case 2:
                ListSales();
                break;
            case 3:
                break;
            }
        } while (option != 4);
    }

    public void serviceFee() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("Service Fee");
            System.out.println("1 - Launch Service Fee");
            System.out.println("2 - List All Service Fees");
            System.out.println("3 - List Service Fees by ID");
            System.out.println("4 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                LaunchFee();
                break;
            case 2:
                ListFee();
                break;
            case 3:
                break;
            }
        } while (option != 4);
    }

    public void rotatePayroll() {

    }

    public void payoutSchedule() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Payout Schedule");
        System.out.println("1 - List All Payment Schedules");
        System.out.println("2 - List Payment Schedules by ID");
        System.out.println("3 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        }
    }

    public void run() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("===============FOLHA DE PAGAMENTO===============");
            System.out.println("1  - Employees");
            System.out.println("2  - Timecard");
            System.out.println("3  - Sales");
            System.out.println("4  - Service Fee");
            System.out.println("5  - Rotate Payroll");
            System.out.println("6  - Payout Schedule");
            System.out.println("7  - Undo");
            System.out.println("8  - Redo");
            System.out.println("9  - Create New Payout Schedule");
            System.out.println("0 - Exit");
            System.out.println("================================================");
            option = op.nextInt();
            switch (option) {
            case 1:
                Employees();
                break;
            case 2:
                Timecard();
                break;
            case 3:
                Sales();
                break;
            case 4:
                serviceFee();
                break;
            case 5:
                rotatePayroll();
                break;
            case 6:
                payoutSchedule();
                break;
            case 7:

                break;
            case 8:

                break;
            case 9:
                break;
            case 0:
                System.exit(0);
                break;
            }
        } while (option != 0);
    }
}