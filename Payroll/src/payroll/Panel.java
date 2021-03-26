package payroll;

import java.util.Scanner;

import payroll.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    public int id = 20210000;
    public int id_syndicate = 0000;
    public int size = 0; // current size employee array
    public int size_timecard = 0; // current size timecard array
    public int size_sales = 0; // current size timecard array
    public int size_fee = 0; // current size timecard array
    public int attr_employee = 12;
    public String[][] employee = new String[500][attr_employee];
    public List<Employee> list_employee = new ArrayList<Employee>();

    // [i][0] = Name
    // [i][1] = Address
    // [i][2] = Type (0 - hourly, 1 - salaried, 2 - commissioned)
    // [i][3] = Hourly Value
    // [i][4] = Salary
    // [i][5] = Comission (1 - yes, 2 - no)
    // [i][6] = id
    // [i][7] = active (y - yes | n - no)
    // [i][8] = Payment Method (0 - Check by the post office, 1 - Check in Person, 2
    // - Bank Account)
    // [i][9] = sindicalist (1 - yes, 2 - no)
    // [i][10] = id_syndicate
    // [i][11] = Syndicate fee

    public String[][] Timecard = new String[500][4];
    // [i][0] = id employee
    // [i][1] = Timecard login
    // [i][2] = Timecard logout
    // [i][3] = Date

    public String[][] salesMade = new String[500][3];
    // [i][0] = id employee
    // [i][1] = Value
    // [i][2] = Sold date

    public String[][] serviceFee = new String[500][2];
    // [i][0] = id employee
    // [i][1] = Value

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
        Scanner sc = new Scanner(System.in);
        int comissioned = 0;
        Double salary = 0.00;
        System.out.println("Type the name:");
        String name = sc.nextLine();
        System.out.println("Type the adrress:");
        String address = sc.nextLine();
        System.out.println("Type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
        int type_employee = sc.nextInt();
        if (type_employee == 0) {
            System.out.println("Type the hourly wage:");
            salary = sc.nextDouble();
        } else if (type_employee == 1) {
            System.out.println("Type the salary:");
            salary = sc.nextDouble();
        } else if (type_employee == 2) {
            System.out.println("Type the salary:");
            salary = sc.nextDouble();
            comissioned = 1;
        }
        System.out.println("Type Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
        int payment_method = sc.nextInt();
        System.out.printf("Does %s belong to syndicate? (1 - yes, 2 - no)\n", name);
        int sindicalist = sc.nextInt();
        System.out.println("Successful registration.");
        list_employee.add(new Employee(++id, name, address));
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

    public boolean isRegistered(String id) {
        for (int i = 0; i < size; i++) {
            if ((id.equals(employee[i][6])) && (employee[i][7].equals("y"))) {
                return true;
            }
        }
        return false;
    }

    public int findEmployeeTimecardId(String id, String logout_date) {
        for (int i = 0; i < size_timecard; i++) {
            if (id.equals(Timecard[i][0]) && logout_date.equals(Timecard[i][3])) {
                return i;
            }
        }
        return -1;
    }

    public void removeEmployee() {
        int index = findEmployee();
        if (index != -1) {
            list_employee.remove(index);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void LaunchSales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();
        if (isRegistered(validate_id)) {
            for (int i = size_sales; i < size_sales + 1; i++) {
                salesMade[i][0] = validate_id;
                System.out.println("Enter sale value:");
                salesMade[i][1] = sc.nextLine();
                System.out.println("Enter sale date:");
                salesMade[i][2] = sc.nextLine();
                System.out.println("=== Successful registration Sales. ===");
            }
            ++size_sales;
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void ListSales() {
        System.out.println("==========");
        System.out.println("|Employee ID|                  | Results from date |                  | Sale Value |");
        for (int i = 0; i < size_sales; i++) {
            System.out.printf("  %s                           %s                             %s\n", salesMade[i][0],
                    salesMade[i][2], salesMade[i][1]);
        }
        System.out.println("==========");
    }

    public void ListAllEmployee() {
        System.out.println("___________________________");
        for (Employee listE : list_employee) {
            System.out.println(listE);
        }
        System.out.println("___________________________");
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
                //int oldAttr = list_employee.get(index).getType_employee();
                System.out.println("Type the new type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
                attr = sc.nextLine();
                //list_employee.get(index).setType_employee(Integer.parseInt(attr));
                if (attr.equals("0")) {
                    System.out.println("Type the hourly wage:");
                    attr = sc.nextLine();
                    //list_employee.get(index).setSalary(Double.parseDouble(attr));
                   
                } else if (attr.equals("1")) {
                    System.out.println("Type the salary:");
                    attr = sc.nextLine();
                    //list_employee.get(index).setSalary(Double.parseDouble(attr));
                    
                } else if (attr.equals("2")) {
                    System.out.println("Type the salary:");
                    attr = sc.nextLine();
                    //list_employee.get(index).setSalary(Double.parseDouble(attr));
                    //list_employee.get(index).setComissioned(1);
                }
            } else if (op.equals("3")) {
                System.out.println(
                        "Select the new Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
                attr = sc.nextLine();
                //list_employee.get(index).setPayment_method(Integer.parseInt(attr));
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
        if (isRegistered(validate_id)) {
            for (int i = size_timecard; i < size_timecard + 1; i++) {
                Timecard[i][0] = validate_id;
                System.out.println("Enter today's date (dd/mm/aa)");
                Timecard[i][3] = sc.nextLine();
                System.out.println("Enter the current time (hh:mm)");
                Timecard[i][1] = sc.nextLine();
                Timecard[i][2] = "";
                System.out.println("=== Successful registration login. ===");
            }
            ++size_timecard;
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void Logout() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();
        if (isRegistered(validate_id)) {
            System.out.println("Enter logout's date (dd/mm/aa)");
            String logout_date = sc.nextLine();
            int index_employee_timecard = findEmployeeTimecardId(validate_id, logout_date);
            if (index_employee_timecard != -1) {
                System.out.println("Enter the current time (hh:mm)");
                String logout_time = sc.nextLine();
                Timecard[index_employee_timecard][2] = logout_time;
                System.out.println("=== Successful registration logout. ===");
            } else {
                System.out.println("Data não encontrada");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void ListTimecard() {
        System.out.println("==========");
        System.out.println(
                "|Employee ID|               | Results from date |               | Login |               | Logout |");
        for (int i = 0; i < size_timecard; i++) {
            System.out.printf("  %s                        %s                        %s                    %s\n",
                    Timecard[i][0], Timecard[i][3], Timecard[i][1], Timecard[i][2]);
        }
    }

    public void LaunchFee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID employee:");
        String validate_id = sc.nextLine();
        if (isRegistered(validate_id)) {
            for (int i = size_fee; i < size_fee + 1; i++) {
                serviceFee[i][0] = validate_id;
                System.out.println("Enter TAX value:");
                serviceFee[i][1] = sc.nextLine();
                System.out.println("=== Successful registration fee. ===");
            }
            ++size_fee;
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void ListFee() {
        System.out.println("==========");
        System.out.printf("|Employee ID|                  | Value |\n");
        for (int i = 0; i < size_fee; i++) {
            System.out.printf("  %s                      %s\n", serviceFee[i][0], serviceFee[i][1]);
        }
        System.out.println("==========");
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