package payroll.payment;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.StringTokenizer;

import payroll.employee.model.Employee;
import payroll.employee.model.Salaried;
import payroll.payment.model.Payslip;
import payroll.utils.Utils;

public class GeneratePayslipEmployee {
    public static void generatePaymentSalariedMonth(Salaried sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            // lastPayment = lastPayment.plusDays(30);
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            String dateMonthString = String.valueOf(date.getMonth());
            String referenceMonthAux = lastPayslip.getReferenceMonth();
            if (date.isAfter(lastPayment) && !(dateMonthString.equals(referenceMonthAux))) {
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
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            Double basicSalary = sEmployee.getSalary(), liquidValue = 0.00;
            double tax = 0.00;
            double addTax = 0.00;
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date);
                liquidValue = basicSalary - tax - addTax;
            } else {
                liquidValue = basicSalary;
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void generatePaymentSalariedWeekly(Salaried sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (date.isAfter(lastPayment)) {
                Double liquidValue = 0.00;
                Double basicSalary = sEmployee.getSalary();
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date, lastPayment);
                    liquidValue = (basicSalary / 4) - tax - addTax;
                } else {
                    liquidValue = (basicSalary / 4);
                }
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            Double basicSalary = (sEmployee.getSalary()), liquidValue = 0.00;
            double tax = 0.00;
            double addTax = 0.00;
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date);
                liquidValue = (basicSalary / 4) - tax - addTax;
            } else {
                liquidValue = (basicSalary / 4);
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void generatePaymentSalariedBiWeekly(Salaried sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            int lastWeekPayment = Utils.weeklyDifference(lastPayment, date);
            if (date.isAfter(lastPayment) && (lastWeekPayment == 2)) {
                Double liquidValue = 0.00;
                Double basicSalary = sEmployee.getSalary();
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date, lastPayment);
                    liquidValue = (basicSalary / 2) - tax - addTax;
                } else {
                    liquidValue = (basicSalary / 2);
                }
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            Double basicSalary = (sEmployee.getSalary()), liquidValue = 0.00;
            double tax = 0.00;
            double addTax = 0.00;
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date);
                liquidValue = (basicSalary / 2) - tax - addTax;
            } else {
                liquidValue = (basicSalary / 2);
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void genEmployee(Employee selectedEmployee, String scheduleString, String monthSchedule,
            String daySchedule, String weekSchedule, List<Payslip> payslip, LocalDate date, int daysHolidayOrWeekn,
            LocalDate holidays[]) {
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
                    generatePaymentSalariedMonth(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            } else if (daySchedule.equals(dayCurrent)) {
                generatePaymentSalariedMonth(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
            }
        } else {
            weekSchedule = st.nextToken(" ");
            daySchedule = st.nextToken(" ");
            String dayWeek = String.valueOf(daySchedule).toUpperCase();
            String dayWeekCurrent = String.valueOf(date.getDayOfWeek());
            if (weekSchedule.equals("1")) {
                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentSalariedWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            } else {

                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentSalariedBiWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            }
        }
    }
}
