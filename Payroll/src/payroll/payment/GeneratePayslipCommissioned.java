package payroll.payment;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.StringTokenizer;

import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Sales;
import payroll.payment.model.Payslip;
import payroll.utils.Utils;

public class GeneratePayslipCommissioned {

    public static void generatePaymentCommissionedBiWeekly(Commissioned sEmployee, LocalDate date,
            List<Payslip> payslip, LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        Double liquidValue = 0.00, calcComission = 0.00, ttlComission = 0.00;
        List<Sales> getSales = sEmployee.getSales();
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
                Double basicSalary = sEmployee.getSalary();
                Double comission = sEmployee.getComission() / 100.0;
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), oldData, lastPayment);
                }
                for (Sales sSales : getSales) {
                    if ((sSales.getDate().isBefore(date) || date.isEqual(sSales.getDate()))
                            && sSales.getDate().isAfter(lastPayment)) {
                        calcComission = sSales.getValue() * (double) comission;
                        liquidValue += calcComission;
                        ttlComission += calcComission;
                    }
                }
                liquidValue += (basicSalary / 2) - tax - addTax;
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth,
                        ttlComission);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            Double basicSalary = sEmployee.getSalary();
            Double comission = sEmployee.getComission() / 100.0;
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
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), oldData);
            }
            for (Sales sSales : getSales) {
                if ((sSales.getDate().isBefore(date) || date.isEqual(sSales.getDate()))) {
                    calcComission = sSales.getValue() * (double) comission;
                    liquidValue += calcComission;
                    ttlComission += calcComission;
                }
            }
            liquidValue += (basicSalary / 2) - tax - addTax;
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth, ttlComission);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void genEmployee(Employee selectedEmployee, String scheduleString, String monthSchedule,
            String daySchedule, String weekSchedule, List<Payslip> payslip, LocalDate date, int daysHolidayOrWeekn,
            LocalDate holidays[]) {
        Commissioned sEmployee = (Commissioned) selectedEmployee;
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
                    // generatePaymentSalariedMonth(sEmployee, date, payslip, holidays,
                    // daysHolidayOrWeekn);
                }
            } else if (daySchedule.equals(dayCurrent)) {
                // generatePaymentSalariedMonth(sEmployee, date, payslip, holidays,
                // daysHolidayOrWeekn);
            }
        } else {
            weekSchedule = st.nextToken(" ");
            daySchedule = st.nextToken(" ");
            String dayWeek = String.valueOf(daySchedule).toUpperCase();
            String dayWeekCurrent = String.valueOf(date.getDayOfWeek());
            if (weekSchedule.equals("1")) {
                if (dayWeekCurrent.equals(dayWeek)) {
                    // generatePaymentSalariedWeekly(sEmployee, date, payslip, holidays,
                    // daysHolidayOrWeekn);
                }
            } else {

                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentCommissionedBiWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            }
        }
    }
}
