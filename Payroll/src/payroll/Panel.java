package payroll;

import java.util.Scanner;

import payroll.employee.MenuEmployee;
import payroll.employee.MenuTimecard;
import payroll.employee.model.Employee;
import payroll.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    public List<Employee> list_employee = new ArrayList<Employee>();
    public static int idSyndicateInt = 0;

    public void Employees() {
        int option;
        String tmp ="";
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Employees ---");
            System.out.println("1 - Add Employees");
            System.out.println("2 - Remove Employees");
            System.out.println("3 - Edit Employees");
            System.out.println("4 - List All Employees");
            System.out.println("5 - Search Employees");
            System.out.println("6 - Back");
            tmp = Utils.consoleReadInputInteger(tmp, op, 1, 7);
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
        String option="";
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
            option = Utils.consoleReadInputInteger(option, op, 0, 9);
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