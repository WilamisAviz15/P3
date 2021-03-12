import java.util.Scanner;

public class Main {
    public static int id = 20210000;
    public static int id_syndicate = 0000;
    public static int size = 0; // current size employee array
    public static int size_timecard = 0; // current size timecard array
    public static int attr_employee = 12;
    public static String[][] employee = new String[500][attr_employee];

    // [i][0] = Name
    // [i][1] = Address
    // [i][2] = Type (0 - hourly, 1 - salaried, 2 - commissioned)
    // [i][3] = Hourly Value
    // [i][4] = Salary
    // [i][5] = Comission (1 - yes, 2 - no)
    // [i][6] = id
    // [i][7] = active
    // [i][8] = Payment Method (0 - Check by the post office, 1 - Check in Person, 2
    // - Bank Account)
    // [i][9] = sindicalist (1 - yes, 2 - no)
    // [i][10] = id_syndicate
    // [i][11] = Syndicate fee

    public static String[][] Timecard = new String[500][4];
    // [i][0] = id employee
    // [i][1] = Timecard login
    // [i][2] = Timecard logout
    // [i][3] = Date

    public static String[][] salesMade = new String[500][3];
    // [i][0] = id employee
    // [i][1] = Value
    // [i][2] = Sold date

    public static void Employees() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Employees ---");
            System.out.println("1 - Add Employees");
            System.out.println("2 - Remove Employees");
            System.out.println("3 - Edit Employees");
            System.out.println("4 - List Employees");
            System.out.println("5 - Back");
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
                listEmployee();
                break;
            case 5:
                break;
            }
        } while (option != 5);
    }

    public static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        for (int i = size; i < size + 1; i++) {
            System.out.println("Type the name:");
            employee[i][0] = sc.nextLine();
            System.out.println("Type the adrress:");
            employee[i][1] = sc.nextLine();
            System.out.println("Type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
            employee[i][2] = sc.nextLine();
            if (employee[i][2].equals("0")) {
                System.out.println("Type the hourly wage:");
                employee[i][3] = sc.nextLine();
            } else if (employee[i][2].equals("1")) {
                System.out.println("Type the salary:");
                employee[i][4] = sc.nextLine();
            } else if (employee[i][2].equals("2")) {
                System.out.println("Type the salary:");
                employee[i][4] = sc.nextLine();
                employee[i][5] = "1";
            }
            employee[i][6] = Integer.toString(++id);
            employee[i][7] = "y";
            System.out.println(
                    "Type Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
            employee[i][8] = sc.nextLine();
            System.out.printf("Does %s belong to sindicate? (1 - yes, 2 - no)\n", employee[i][0]);
            employee[i][9] = sc.nextLine();
            if (employee[i][9].equals("1")) {

            } else if (employee[i][9].equals("2")) {
                employee[i][10] = "";
                employee[i][11] = "";
            }
            System.out.println("Successful registration.");
        }
        ++size;
    }

    public static int findEmployee() {
        String id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        id = sc.nextLine();
        for (int i = 0; i < size; i++) {
            if (id.equals(employee[i][6])) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isRegistered(String id) {
        for (int i = 0; i < size; i++) {
            if (id.equals(employee[i][6])) {
                return true;
            }
        }
        return false;
    }

    public static void removeEmployee() {
        int index = findEmployee();
        if (index != -1) {
            if (employee[index][7].equals("y")) {
                employee[index][7] = "n";
                System.out.println("Employee removed.");
            } else {
                System.out.println("Employee not found.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void listEmployee() {
        String type_employee = "";
        String payment_method = "";
        for (int i = 0; i < size; i++) {
            if (employee[i][7].equals("y")) {
                System.out.println("==========");
                System.out.printf("Employee ID: %s\n", employee[i][6]);
                System.out.printf("Name: %s\n", employee[i][0]);
                System.out.printf("Address: %s\n", employee[i][1]);
                if (employee[i][2].equals("0")) {
                    type_employee = "hourly";
                } else if (employee[i][2].equals("1")) {
                    type_employee = "Assalaried";
                } else if (employee[i][2].equals("2")) {
                    type_employee = "Comissioned";
                }
                System.out.printf("Type of employee: %s\n", type_employee);
                if (employee[i][2].equals("0")) {
                    System.out.printf("Salary per hour: %s\n", employee[i][3]);
                } else if (employee[i][2].equals("1")) {
                    System.out.printf("Salary: %s\n", employee[i][4]);
                } else if (employee[i][2].equals("2")) {
                    System.out.printf("Salary: %s\n", employee[i][4]);
                }
                if (employee[i][8].equals("0")) {
                    payment_method = "Check by the post office";
                } else if (employee[i][8].equals("1")) {
                    payment_method = "Check in Person";
                } else if (employee[i][8].equals("2")) {
                    payment_method = "Bank Account";
                }
                System.out.printf("Payment Method: %s\n", payment_method);
                System.out.println("==========");
            }
        }
    }

    public static void editEmployee() {
        String id, op, attr;
        Scanner sc = new Scanner(System.in);
        int index = findEmployee();
        if (index != -1) {
            if (employee[index][7].equals("y")) {
                System.out.printf("Employed selected: %s. What do you want to edit?\n", employee[index][0]);
                System.out.println("0 - Name");
                System.out.println("1 - Address");
                System.out.println("2 - Type of employee");
                System.out.println("3 - Paymento Method");
                op = sc.nextLine();
                if (op.equals("0")) {
                    System.out.println("Type the new name");
                    attr = sc.nextLine();
                    employee[index][0] = attr;
                    System.out.println("Name changed successfully.");
                } else if (op.equals("1")) {
                    System.out.println("Type the new address");
                    attr = sc.nextLine();
                    employee[index][1] = attr;
                    System.out.println("Address changed successfully.");
                } else if (op.equals("2")) {
                    String oldAttr = employee[index][2];
                    System.out.println("Type the new type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
                    attr = sc.nextLine();
                    employee[index][2] = attr;
                    if (attr.equals("0")) {
                        System.out.println("Type the hourly wage:");
                        employee[index][3] = sc.nextLine();
                        if (oldAttr.equals("2")) {
                            employee[index][5] = "2";
                        }
                    } else if (attr.equals("1")) {
                        System.out.println("Type the salary:");
                        employee[index][4] = sc.nextLine();
                        if (oldAttr.equals("2")) {
                            employee[index][5] = "2";
                        }
                    } else if (attr.equals("2")) {
                        System.out.println("Type the salary:");
                        employee[index][4] = sc.nextLine();
                        employee[index][5] = "1";
                    }
                } else if (op.equals("3")) {
                    System.out.println(
                            "Select the new Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
                    employee[index][8] = sc.nextLine();
                }
                System.out.println("Type changed successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void Login() {
        Scanner sc = new Scanner(System.in);
        for (int i = size_timecard; i < size_timecard + 1; i++) {
            System.out.println("Enter ID employee:");
            String validate_id = sc.nextLine();
            if (isRegistered(validate_id)) {
                Timecard[i][0] = validate_id;
                System.out.println("Enter today's date (dd/mm/aa)");
                Timecard[i][3] = sc.nextLine();
                System.out.println("Enter the current time (hh:mm)");
                Timecard[i][1] = sc.nextLine();
                Timecard[i][2] = "";
            } else {
                System.out.println("Employee not found.");
            }
        }
        ++size_timecard;
    }

    public static void ListTimecard() {
        for (int i = 0; i < size_timecard; i++) {
            System.out.println("==========");
            System.out.printf("Employee ID: %s\n", Timecard[i][0]);
            System.out.printf("Results from date: %s\n", Timecard[i][3]);
            System.out.printf("Login: %s\n", Timecard[i][1]);
            System.out.printf("Logout: %s\n", Timecard[i][2]);
            System.out.println("==========");
        }
    }

    public static void Timecard() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Timecard");
        System.out.println("1 - Timecard login");
        System.out.println("2 - Timecard logout");
        System.out.println("3 - List Timecards");
        System.out.println("4 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            Login();
            break;
        case 2:
            break;
        case 3:
            ListTimecard();
            break;
        case 4:
            break;
        }
    }

    public static void Sales() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Sales");
        System.out.println("1 - Launch Sale");
        System.out.println("2 - List Sales");
        System.out.println("3 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        }
    }

    public static void serviceFee() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Service Fee");
        System.out.println("1 - Launch Service Fee");
        System.out.println("2 - List Service Fees");
        System.out.println("3 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        }
    }

    public static void rotatePayroll() {

    }

    public static void payoutSchedule() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Payout Schedule");
        System.out.println("1 - List Payment Schedules");
        System.out.println("2 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        }
    }

    public static void main(String[] args) {
        int option;
        String employee[][];
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
