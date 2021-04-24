package payroll.payment;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Salaried;
import payroll.payment.model.Payslip;
import payroll.utils.Utils;

public class MenuPayroll {

    public static void Menu(List<Employee> list_employee) {
        int option;
        String tmp = "";
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Payroll ---");
            System.out.println("1 - Rotate Payroll");
            System.out.println("2 - List payroll");
            System.out.println("3 - Back");

            tmp = Utils.consoleReadInputIntegerOptions(tmp, op, 1, 4);
            option = Integer.parseInt(tmp);
            switch (option) {
            case 1:
                LocalDate date = Utils.validateDate(op);
                rotate(list_employee, date);
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
                        System.out.println("#######PAYSLIP REF: " + pAux.getDate().getMonth() + "########");
                        System.out.println("Date to payment: " + pAux.getDate());
                        System.out.println("Basic Salary: " + pAux.getBasicPay());
                        System.out.println("Tax: " + pAux.getTax());
                        System.out.println("Additional Tax: " + pAux.getAdditionaTax());
                        System.out.println("Net Salary: " + pAux.getNetPay());
                        System.out.println("    ##Payment method## ");
                        System.out.println(sEmployee.getPaymentMethod().toString());
                        System.out.println("#######END PAYSLIP REF: " + pAux.getDate().getMonth() + "########");
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
            } else if (sEmployee instanceof Hourly) {
                System.out.println(">>>>>>>>> Type: " + sEmployee.getClass().getSimpleName() + " <<<<<<<<<");
            }
        }
        System.out.println("#################END PAYSLIP###############");
    }

    public static void generatePaymentSalaried(Salaried sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[]) {
        LocalDate oldDate = date;
        LocalDate lastPayment;
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            // lastPayment = lastPayment.plusDays(30);
            int daysHolidayOrWeekn = Utils.countHolidaysOrWeekend(date, holidays);
            if (daysHolidayOrWeekn != 0) {
                date = date.plusDays(daysHolidayOrWeekn);
            }
            if (date.isAfter(lastPayment) && !(lastPayment.getMonth().equals(date.getMonth()))) {
                Double liquidValue = 0.00;
                Double basicSalary = sEmployee.getSalary();
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date, lastPayment);
                    liquidValue = basicSalary - tax - addTax;
                } else {
                    liquidValue = basicSalary;
                }
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax);
                sEmployee.getPayslipSheet().add(newPayslip);
                if (daysHolidayOrWeekn == 0) {
                    System.out.println("Payslip successfully generated");
                } else {
                    System.out.println("Payslip successfully generated");
                    System.out.println("### The date to payment is holiday or weekend" + oldDate + "###");
                    System.out.println("### In this case the payslip will be paid to: " + date + "###");
                }
            }
        } else {
            int daysHolidayOrWeekn = Utils.countHolidaysOrWeekend(date, holidays);
            Double basicSalary = sEmployee.getSalary(), liquidValue = 0.00;
            double tax = 0.00;
            double addTax = 0.00;
            if (daysHolidayOrWeekn != 0) {
                date = date.plusDays(daysHolidayOrWeekn);
            }
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date);
                liquidValue = basicSalary - tax - addTax;
            } else {
                liquidValue = basicSalary;
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax);
            sEmployee.getPayslipSheet().add(newPayslip);
            if (daysHolidayOrWeekn == 0) {
                System.out.println("Payslip successfully generated");
            } else {
                System.out.println("Payslip successfully generated");
                System.out.println("### The date to payment is holiday or weekend " + oldDate + "###");
                System.out.println("### In this case the payslip will be paid to: " + date + "###");
            }
        }
    }

    public static void rotate(List<Employee> list_employee, LocalDate date) {
        String scheduleString, monthSchedule, weekSchedule = "", daySchedule = "";
        List<Payslip> payslip;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        LocalDate holidays[] = { LocalDate.of(year, 1, 01), LocalDate.of(year, 4, 21), LocalDate.of(year, 6, 03),
                LocalDate.of(year, 9, 07), LocalDate.of(year, 10, 12), LocalDate.of(year, 11, 02),
                LocalDate.of(year, 11, 15), LocalDate.of(year, 12, 25) };
        Scanner sc = new Scanner(System.in);
        for (Employee selectedEmployee : list_employee) {
            if (selectedEmployee instanceof Salaried) {
                Salaried sEmployee = (Salaried) selectedEmployee;
                scheduleString = sEmployee.getPaymentMethod().getPaySchedule();
                StringTokenizer st = new StringTokenizer(scheduleString);
                payslip = sEmployee.getPayslipSheet();
                monthSchedule = st.nextToken(" ");
                if (monthSchedule.equals("monthly")) {
                    daySchedule = st.nextToken(" ");
                    String dayCurrent = String.valueOf(date.getDayOfMonth());
                    if (daySchedule.equals("$")) {
                        LocalDate lastDayofMonth = LocalDate.now().withMonth(date.getMonthValue())
                                .with(TemporalAdjusters.lastDayOfMonth());
                        if (date.equals(lastDayofMonth)) {
                            generatePaymentSalaried(sEmployee, date, payslip, holidays);
                        }
                    } else if (daySchedule.equals(dayCurrent)) {
                        generatePaymentSalaried(sEmployee, date, payslip, holidays);
                    }
                } else {
                    weekSchedule = st.nextToken(" ");
                    daySchedule = st.nextToken(" ");
                }
            }
        }
    }
}
