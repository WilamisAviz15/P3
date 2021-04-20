package payroll;

import java.util.Scanner;
import payroll.employee.Employee;
import payroll.employee.MenuEmployee;
import payroll.employee.MenuTimecard;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    public List<Employee> list_employee = new ArrayList<Employee>();
    public static int idSyndicateInt = 0;

    public void Employees() {
        int option;
        String tmp;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Employees ---");
            System.out.println("1 - Add Employees");
            System.out.println("2 - Remove Employees");
            System.out.println("3 - Edit Employees");
            System.out.println("4 - List All Employees");
            System.out.println("5 - Search Employees");
            System.out.println("6 - Back");
            while (true) {
                tmp = op.nextLine();
                try {
                    int tmp_op = Integer.parseInt(tmp);
                    if (tmp_op >= 0 && tmp_op < 7) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again..\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            option = Integer.parseInt(tmp);
            switch (option) {
            case 1:
                list_employee.add(MenuEmployee.addEmployee());
                break;
            case 2:
                MenuEmployee.removeEmployee(list_employee);
                break;
            case 3:
                MenuEmployee.editEmployee(list_employee);
                break;
            case 4:
                MenuEmployee.ListAllEmployee(list_employee);
                break;
            case 5:
                MenuEmployee.listEmployeeById(list_employee);
                break;
            }
        } while (option != 6);
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
        String option;
        int opc;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("===============PAYROLL===============");
            System.out.println("1  - Employees");
            System.out.println("2  - Timecard");
            System.out.println("3  - Launch Sales");
            System.out.println("4  - Launch Service Fee");
            System.out.println("5  - Rotate Payroll");
            System.out.println("6  - Payout Schedule");
            System.out.println("7  - Undo");
            System.out.println("8  - Redo");
            System.out.println("0 - Exit");
            System.out.println("================================================");
            while (true) {
                option = op.nextLine();
                try {
                    int option_int = Integer.parseInt(option);
                    if (option_int >= 0 && option_int < 9) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            opc = Integer.parseInt(option);
            switch (opc) {
            case 1:
                Employees();
                break;
            case 2:
                MenuTimecard.Timecard(list_employee);
                break;
            case 3:
                MenuEmployee.LaunchSales(list_employee);
                break;
            case 4:
                MenuEmployee.LaunchFee(list_employee);
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
            case 0:
                System.exit(0);
                break;
            }
        } while (opc != 0);
    }
}