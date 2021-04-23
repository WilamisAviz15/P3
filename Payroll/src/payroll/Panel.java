package payroll;

import java.util.Scanner;
import java.util.Stack;

import payroll.employee.MenuEmployee;
import payroll.employee.MenuTimecard;
import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Sales;
import payroll.payment.MenuPayoutSchedule;
import payroll.payment.model.PaymentSchedule;
import payroll.syndicate.AdditionalFee;
import payroll.utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Panel {
    public List<Employee> list_employee = new ArrayList<Employee>();
    PaymentSchedule paySchedules = new PaymentSchedule();
    public static int idSyndicateInt = 0;

    Stack<List<Employee>> undo = new Stack<>();
    Stack<List<Employee>> redo = new Stack<>();

    public void rotatePayroll() {

    }

    public static void LaunchSales(List<Employee> list_employee) {
        int index = MenuEmployee.findEmployee(list_employee);
        Double value;
        LocalDate date;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Commissioned) {
                Commissioned empl = (Commissioned) selectedEmployee;
                System.out.println("Enter sale value");
                tmp = Utils.consoleReadInputDouble(tmp, sc);
                value = Double.parseDouble(tmp);
                System.out.println("Enter day:");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, false);
                int day = Integer.parseInt(tmp);
                System.out.println("Enter month:");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, false);
                int month = Integer.parseInt(tmp);
                System.out.println("Enter year:");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, false);
                int year = Integer.parseInt(tmp);
                date = LocalDate.of(year, month, day);
                Sales sl = new Sales(date, value);
                empl.getSales().add(sl);
            } else {
                System.out.println("Employee is not comissioned.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void LaunchFee(List<Employee> list_employee) {
        int index = MenuEmployee.findEmployee(list_employee);
        LocalDate date;
        String tmp = "";
        Double value;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee.getSyndicate().getActive() == true) {
                System.out.println("Enter day:");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, false);
                int day = Integer.parseInt(tmp);
                System.out.println("Enter month:");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, false);
                int month = Integer.parseInt(tmp);
                System.out.println("Enter year:");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, false);
                int year = Integer.parseInt(tmp);
                date = LocalDate.of(year, month, day);
                System.out.println("Enter value");
                tmp = Utils.consoleReadInputDouble(tmp, sc);
                value = Double.parseDouble(tmp);
                AdditionalFee aF = new AdditionalFee(date, value);
                selectedEmployee.getSyndicate().getAdditionalFee().add(aF);
            } else {
                System.out.println(selectedEmployee.getName()
                        + " does not belongs to syndicate or is inactive to add service fee.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void run() {
        String option = "";
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
            option = Utils.consoleReadInputIntegerOptions(option, op, 0, 9);
            opc = Integer.parseInt(option);
            switch (opc) {
            case 1:
                MenuEmployee.Menu(list_employee, paySchedules, undo);
                break;
            case 2:
                MenuTimecard.Timecard(list_employee, undo);
                break;
            case 3:
                LaunchSales(list_employee);
                break;
            case 4:
                LaunchFee(list_employee);
                break;
            case 5:
                rotatePayroll();
                break;
            case 6:
                MenuPayoutSchedule.Menu(paySchedules);
                break;
            case 7:
                if (undo.size() > 0) {
                    List<Employee> aux = undo.pop();
                    redo.push(Utils.cloneList(list_employee));
                    list_employee = aux;
                    System.out.println("Changes undone successfully.");
                }
                else {
                    System.out.println("There are nothing to undo.");
                }
                break;
            case 8:
                if (redo.size() > 0) {
                    List<Employee> aux = redo.pop();
                    undo.push(Utils.cloneList(list_employee));
                    list_employee = aux;
                    System.out.println("Changes redone successfully.");
                } else {
                    System.out.println("There are nothing to redo.");
                }
                break;
            case 0:
                System.exit(0);
                break;
            }
        } while (opc != 0);
    }
}