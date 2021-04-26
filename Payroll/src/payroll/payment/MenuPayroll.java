package payroll.payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Salaried;
import payroll.payment.model.Payslip;
import payroll.utils.Utils;

public class MenuPayroll {

    public static void Menu(List<Employee> list_employee, Stack<List<Employee>> undo, Stack<List<Employee>> redo) {
        int option;
        String tmp = "";
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Payroll ---");
            System.out.println("1 - Rotate Payroll");
            System.out.println("2 - List all payroll");
            System.out.println("3 - Back");

            tmp = Utils.consoleReadInputIntegerOptions(tmp, op, 1, 4);
            option = Integer.parseInt(tmp);
            switch (option) {
            case 1:
                LocalDate date = Utils.validateDate(op);
                rotate(list_employee, date, undo, redo);
                break;
            case 2:
                printAll(list_employee);
                break;
            case 3:
                break;
            }
        } while (option != 3);
    }

    public static void printAll(List<Employee> list_employee) {
        System.out.println("#################PAYSLIP###############");
        System.out.println();
        System.out.println();
        for (Employee sEmployee : list_employee) {
            System.out.println(">>>>>>>>> Employee: " + sEmployee.getName() + " <<<<<<<<<");
            if (sEmployee instanceof Salaried) {
                System.out.println(">>>>>>>>> Type: " + sEmployee.getClass().getSimpleName() + " <<<<<<<<<");
                if (!sEmployee.getPayslipSheet().isEmpty()) {
                    for (Payslip pAux : sEmployee.getPayslipSheet()) {
                        System.out.println();
                        System.out.println("#######PAYSLIP REF: " + pAux.getReferenceMonth() + "########");
                        System.out.println("Date to payment: " + pAux.getDate());
                        System.out.println("Basic Salary: " + pAux.getBasicPay());
                        System.out.println("Tax: " + pAux.getTax());
                        System.out.println("Additional Tax: " + pAux.getAdditionaTax());
                        System.out.println("Net Salary: " + pAux.getNetPay());
                        System.out.println("    ##Payment method## ");
                        System.out.println(sEmployee.getPaymentMethod().toString());
                        System.out.println("#######END PAYSLIP REF: " + pAux.getReferenceMonth() + "########");
                        System.out.println();
                        System.out.println();
                    }
                } else {
                    System.out.println("No paycheck found.");
                    System.out.println();
                    System.out.println();
                }
            } else if (sEmployee instanceof Commissioned) {
                System.out.println(">>>>>>>>> Type: " + sEmployee.getClass().getSimpleName() + " <<<<<<<<<");
                if (!sEmployee.getPayslipSheet().isEmpty()) {
                    for (Payslip pAux : sEmployee.getPayslipSheet()) {
                        System.out.println();
                        System.out.println("#######PAYSLIP REF: " + pAux.getReferenceMonth() + "########");
                        System.out.println("Date to payment: " + pAux.getDate());
                        System.out.println("Basic Salary: " + pAux.getBasicPay());
                        System.out.println("Comission value: " + pAux.getCommissionsValue());
                        System.out.println("Tax: " + pAux.getTax());
                        System.out.println("Additional Tax: " + pAux.getAdditionaTax());
                        System.out.println("Net Salary: " + pAux.getNetPay());
                        System.out.println("    ##Payment method## ");
                        System.out.println(sEmployee.getPaymentMethod().toString());
                        System.out.println("#######END PAYSLIP REF: " + pAux.getReferenceMonth() + "########");
                        System.out.println();
                        System.out.println();
                    }
                } else {
                    System.out.println("No paycheck found.");
                    System.out.println();
                    System.out.println();
                }
            } else if (sEmployee instanceof Hourly) {
                System.out.println(">>>>>>>>> Type: " + sEmployee.getClass().getSimpleName() + " <<<<<<<<<");
                if (!sEmployee.getPayslipSheet().isEmpty()) {
                    for (Payslip pAux : sEmployee.getPayslipSheet()) {
                        System.out.println();
                        System.out.println("#######PAYSLIP REF: " + pAux.getReferenceMonth() + "########");
                        System.out.println("Date to payment: " + pAux.getDate());
                        System.out.println("Value per hour: " + pAux.getBasicPay());
                        System.out.println("Total hours: " + pAux.getHours());
                        System.out.println("Average hours per day: " + pAux.getHours() / pAux.getCountTimecard());
                        System.out.println("Extra hours: " + pAux.getExtrasHours());
                        System.out.println("Tax: " + pAux.getTax());
                        System.out.println("Additional Tax: " + pAux.getAdditionaTax());
                        System.out.println("Net Salary: " + pAux.getNetPay());
                        System.out.println("    ##Payment method## ");
                        System.out.println(sEmployee.getPaymentMethod().toString());
                        System.out.println("#######END PAYSLIP REF: " + pAux.getReferenceMonth() + "########");
                        System.out.println();
                        System.out.println();
                    }
                } else {
                    System.out.println("No paycheck found.");
                    System.out.println();
                    System.out.println();
                }
            }
        }
        System.out.println("#################END PAYSLIP###############");
    }

    public static void rotate(List<Employee> list_employee, LocalDate date, Stack<List<Employee>> undo,
            Stack<List<Employee>> redo) {
        String scheduleString = "", monthSchedule = "", weekSchedule = "", daySchedule = "";
        List<Payslip> payslip = null;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        LocalDate holidays[] = { LocalDate.of(year, 1, 01), LocalDate.of(year, 4, 21), LocalDate.of(year, 6, 03),
                LocalDate.of(year, 9, 07), LocalDate.of(year, 10, 12), LocalDate.of(year, 11, 02),
                LocalDate.of(year, 11, 15), LocalDate.of(year, 12, 25) };
        LocalDate oldDate = date;
        int daysHolidayOrWeekn = Utils.countHolidaysOrWeekend(date, holidays);
        Scanner sc = new Scanner(System.in);
        
        undo.push(Utils.cloneList(list_employee));
        redo.clear();

        for (Employee selectedEmployee : list_employee) {
            if (selectedEmployee instanceof Salaried) {
                List<Payslip> clone = new ArrayList<Payslip>();
                for (Payslip item : selectedEmployee.getPayslipSheet()) {
                    Payslip p = (Payslip) item;
                    p = (Payslip) new Payslip(item.getBasicPay(), item.getNetPay(), item.getDate(), item.getTax(),
                            item.getAdditionaTax(), item.getReferenceMonth());
                    clone.add(p);
                }
                selectedEmployee.setPayslipSheet(clone);
            } else if (selectedEmployee instanceof Commissioned) {
                List<Payslip> clone = new ArrayList<Payslip>();
                for (Payslip item : selectedEmployee.getPayslipSheet()) {
                    Payslip p = (Payslip) item;
                    p =  new Payslip(item.getBasicPay(), item.getNetPay(), item.getDate(), item.getTax(),
                            item.getAdditionaTax(), item.getReferenceMonth(), item.getCommissionsValue(),
                            item.getLastPaidIsHoliday());
                    clone.add(p);
                }
                selectedEmployee.setPayslipSheet(clone);
            } else if (selectedEmployee instanceof Hourly) {
                List<Payslip> clone = new ArrayList<Payslip>();
                for (Payslip item : selectedEmployee.getPayslipSheet()) {
                    Payslip p = (Payslip) item;
                    p = new Payslip(item.getBasicPay(), item.getNetPay(), item.getHours(), item.getExtrasHours(),
                            item.getDate(), item.getTax(), item.getAdditionaTax(), item.getReferenceMonth(),
                            item.getCountTimecard(), item.getLastPaidIsHoliday());
                    clone.add(p);
                }
                selectedEmployee.setPayslipSheet(clone);
            }
        }



        for (Employee selectedEmployee : list_employee) {
            if (selectedEmployee instanceof Salaried) {
                GeneratePayslipEmployee.genEmployee(selectedEmployee, scheduleString, monthSchedule, daySchedule,
                        weekSchedule, payslip, date, daysHolidayOrWeekn, holidays);
            } else if (selectedEmployee instanceof Commissioned) {
                GeneratePayslipCommissioned.genEmployee(selectedEmployee, scheduleString, monthSchedule, daySchedule,
                        weekSchedule, payslip, date, daysHolidayOrWeekn, holidays);
            } else if (selectedEmployee instanceof Hourly) {
                GeneratePayslipHourly.genEmployee(selectedEmployee, scheduleString, monthSchedule, daySchedule,
                        weekSchedule, payslip, date, daysHolidayOrWeekn, holidays);
            }
        }
        if (daysHolidayOrWeekn == 0) {
            System.out.println("Payslip successfully generated");
        } else {
            System.out.println("Payslip successfully generated");
            System.out.println("### The date to payment is holiday or weekend" + oldDate + "###");
            date = date.plusDays(daysHolidayOrWeekn);
            System.out.println("### In this case the payslip will be paid to: " + date + "###");
        }
    }
}
