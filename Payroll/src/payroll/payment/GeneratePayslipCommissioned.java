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

    public static void generatePaymentCommissionedMonthly(Commissioned sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        Double liquidValue = 0.00, calcComission = 0.00, ttlComission = 0.00;
        boolean lastPaymentIsHoliday = false;
        List<Sales> getSales = sEmployee.getSales();
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
                lastPaymentIsHoliday = true;
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            String dateMonthString = String.valueOf(date.getMonth());
            String referenceMonthAux = lastPayslip.getReferenceMonth();
            if (date.isAfter(lastPayment) && !(dateMonthString.equals(referenceMonthAux))) {
                Double basicSalary = sEmployee.getSalary();
                Double comission = sEmployee.getComission() / 100.0;
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date, lastPayment);
                }
                for (Sales sSales : getSales) {
                    if ((sSales.getDate().isBefore(oldData) || oldData.isEqual(sSales.getDate()))
                            && (sSales.getDate().isAfter(lastPayment) || sSales.getDate().isEqual(lastPayment))) {
                        calcComission = sSales.getValue() * (double) comission;
                        liquidValue += calcComission;
                        ttlComission += calcComission;
                    }
                }
                liquidValue += basicSalary - tax - addTax;
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth,
                        ttlComission, lastPaymentIsHoliday);
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
                lastPaymentIsHoliday = true;
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), oldData);
            }
            int i = 0;
            for (Sales sSales : getSales) {
                if (sSales.getDate().isBefore(oldData) || oldData.isEqual(sSales.getDate())) {
                    calcComission = sSales.getValue() * (double) comission;
                    liquidValue += calcComission;
                    ttlComission += calcComission;
                }
            }
            liquidValue += basicSalary - tax - addTax;
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth, ttlComission,
                    lastPaymentIsHoliday);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void generatePaymentCommissionedWeekly(Commissioned sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        Double liquidValue = 0.00, calcComission = 0.00, ttlComission = 0.00;
        boolean lastPaymentIsHoliday = false;
        List<Sales> getSales = sEmployee.getSales();
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
                lastPaymentIsHoliday = true;
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (date.isAfter(lastPayment)) {
                Double basicSalary = sEmployee.getSalary();
                Double comission = sEmployee.getComission() / 100.0;
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date, lastPayment);
                }
                for (Sales sSales : getSales) {
                    if ((sSales.getDate().isBefore(oldData) || oldData.isEqual(sSales.getDate()))) {
                        if (lastPayslip.getLastPaidIsHoliday()) {
                            if (sSales.getDate().isAfter(lastPayment) || sSales.getDate().isEqual(lastPayment)) {
                                calcComission = sSales.getValue() * (double) comission;
                                liquidValue += calcComission;
                                ttlComission += calcComission;
                            }
                        } else {
                            if (sSales.getDate().isAfter(lastPayment)) {
                                calcComission = sSales.getValue() * (double) comission;
                                liquidValue += calcComission;
                                ttlComission += calcComission;
                            }
                        }
                    }
                }
                liquidValue += ((basicSalary) / 4) - tax - addTax;
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth,
                        ttlComission, lastPaymentIsHoliday);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
                lastPaymentIsHoliday = true;
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            Double basicSalary = sEmployee.getSalary();
            Double comission = sEmployee.getComission() / 100.0;
            double tax = 0.00;
            double addTax = 0.00;
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date);
            }
            for (Sales sSales : getSales) {
                if (sSales.getDate().isBefore(date) || date.isEqual(sSales.getDate())) {
                    calcComission = sSales.getValue() * (double) comission;
                    liquidValue += calcComission;
                    ttlComission += calcComission;
                }
            }
            liquidValue += ((basicSalary) / 4) - tax - addTax;
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth, ttlComission,
                    lastPaymentIsHoliday);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void generatePaymentCommissionedBiWeekly(Commissioned sEmployee, LocalDate date,
            List<Payslip> payslip, LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        boolean lastPaymentIsHoliday = false;
        Double liquidValue = 0.00, calcComission = 0.00, ttlComission = 0.00;
        List<Sales> getSales = sEmployee.getSales();
        if (!payslip.isEmpty()) {
            int sizePayslip = payslip.size() - 1;
            Payslip lastPayslip = payslip.get(sizePayslip);
            lastPayment = lastPayslip.getDate();
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
                lastPaymentIsHoliday = true;
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            int lastWeekPayment;
            if (lastPayslip.getLastPaidIsHoliday()) {
                lastWeekPayment = Utils.weeklyDifference(lastPayment, date) + 1;
            } else {
                lastWeekPayment = Utils.weeklyDifference(lastPayment, date);
            }

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
                    if ((sSales.getDate().isBefore(date) || date.isEqual(sSales.getDate()))) {
                        if (lastPayslip.getLastPaidIsHoliday()) {
                            if (sSales.getDate().isAfter(lastPayment) || sSales.getDate().isEqual(lastPayment)) {
                                calcComission = sSales.getValue() * (double) comission;
                                liquidValue += calcComission;
                                ttlComission += calcComission;
                            }
                        } else {
                            if (sSales.getDate().isAfter(lastPayment)) {
                                calcComission = sSales.getValue() * (double) comission;
                                liquidValue += calcComission;
                                ttlComission += calcComission;
                            }
                        }
                    }
                }
                liquidValue += (basicSalary / 2) - tax - addTax;
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth,
                        ttlComission, lastPaymentIsHoliday);
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
                lastPaymentIsHoliday = true;
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), oldData);
            }
            for (Sales sSales : getSales) {
                if (sSales.getDate().isBefore(date) || date.isEqual(sSales.getDate())) {
                    calcComission = sSales.getValue() * (double) comission;
                    liquidValue += calcComission;
                    ttlComission += calcComission;
                }
            }
            liquidValue += (basicSalary / 2) - tax - addTax;
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, date, tax, addTax, referenceMonth, ttlComission,
                    lastPaymentIsHoliday);
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
                    generatePaymentCommissionedMonthly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            } else if (daySchedule.equals(dayCurrent)) {
                generatePaymentCommissionedMonthly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
            }
        } else {
            weekSchedule = st.nextToken(" ");
            daySchedule = st.nextToken(" ");
            String dayWeek = String.valueOf(daySchedule).toUpperCase();
            String dayWeekCurrent = String.valueOf(date.getDayOfWeek());
            if (weekSchedule.equals("1")) {
                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentCommissionedWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            } else {

                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentCommissionedBiWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            }
        }
    }
}
