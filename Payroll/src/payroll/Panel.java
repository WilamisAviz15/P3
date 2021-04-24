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
import payroll.syndicate.Syndicate;
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

    public static void LaunchSales(List<Employee> list_employee, Stack<List<Employee>> undo, Stack<List<Employee>> redo) {
        int index = MenuEmployee.findEmployee(list_employee);
        Double value;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Commissioned) {
                undo.push(Utils.cloneList(list_employee));
                redo.clear();
                Commissioned empl = (Commissioned) selectedEmployee;
                List<Sales> t = Utils.cloneListSales(empl.getSales());
                empl.setSales(t);
                System.out.println("Enter sale value");
                tmp = Utils.consoleReadInputDouble(tmp, sc);
                value = Double.parseDouble(tmp);
                LocalDate date = Utils.validateDate(sc);
                Sales sl = new Sales(date, value);
                empl.getSales().add(sl);
                System.out.println("sale added successfully");
            } else {
                System.out.println("Employee is not comissioned.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void LaunchFee(List<Employee> list_employee, Stack<List<Employee>> undo, Stack<List<Employee>> redo) {
        int index = MenuEmployee.findEmployee(list_employee);
        String tmp = "";
        Double value;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee.getSyndicate().getActive() == true) {
                undo.push(Utils.cloneList(list_employee));
                redo.clear();
                Syndicate s = Utils.cloneListSyndicate(selectedEmployee.getSyndicate());
                selectedEmployee.setSyndicate(s);
                List<AdditionalFee> aFX = Utils.cloneListAddFee(selectedEmployee.getSyndicate().getAdditionalFee());
                selectedEmployee.getSyndicate().setAdditionalFee(aFX);
                LocalDate date = Utils.validateDate(sc);
                System.out.println("Enter value");
                tmp = Utils.consoleReadInputDouble(tmp, sc);
                value = Double.parseDouble(tmp);
                AdditionalFee aF = new AdditionalFee(date, value);
                selectedEmployee.getSyndicate().getAdditionalFee().add(aF);
                System.out.println("Additional Fee added successfully");
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
                MenuEmployee.Menu(list_employee, paySchedules, undo, redo);
                break;
            case 2:
                MenuTimecard.Timecard(list_employee, undo, redo);
                break;
            case 3:
                LaunchSales(list_employee, undo, redo);
                break;
            case 4:
                LaunchFee(list_employee, undo, redo);
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
                } else {
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