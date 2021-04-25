package payroll.payment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Timecard;
import payroll.payment.model.Payslip;
import payroll.utils.Utils;

public class GeneratePayslipHourly {

    public static void generatePaymentHourlyMonth(Hourly sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        Double hours = 0.00, extraHours = 0.00, ttlHours = 0.00, liquidValue = 0.00;
        ;
        List<Timecard> getTimeCard = sEmployee.getTimecard();
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
            String dateMonthString = String.valueOf(date.getMonth());
            String referenceMonthAux = lastPayslip.getReferenceMonth();
            if (date.isAfter(lastPayment) && !(dateMonthString.equals(referenceMonthAux))) {
                Double basicSalary = sEmployee.getHourlyValue();
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), oldData, lastPayment);
                }
                int i = 0;
                for (Timecard sTimecard : getTimeCard) {
                    if ((sTimecard.getDate().isBefore(oldData) || oldData.isEqual(sTimecard.getDate()))
                            && (sTimecard.getDate().isAfter(lastPayment) || sTimecard.getDate().isEqual(lastPayment))) {
                        LocalTime login = sTimecard.getLogin();
                        LocalTime logout = sTimecard.getLogout();
                        Duration duration = Duration.between(login, logout);
                        hours = (double) duration.getSeconds() / 3600;
                        ttlHours += hours;
                        if (hours > 8.0) {
                            extraHours = hours - 8.0;
                            liquidValue += 8.0 * basicSalary;
                            liquidValue += extraHours * basicSalary * 1.5;
                            liquidValue -= tax - addTax;
                        } else if (hours >= 0.0 && hours <= 8.0) {
                            liquidValue += hours * basicSalary;
                            liquidValue -= tax - addTax;
                        }
                        i++;
                    }
                }
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, ttlHours, extraHours, date, tax, addTax,
                        referenceMonth, i);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            Double basicSalary = sEmployee.getHourlyValue();
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
            int i = 0;
            for (Timecard sTimecard : getTimeCard) {
                if (sTimecard.getDate().isBefore(oldData) || oldData.isEqual(sTimecard.getDate())) {
                    LocalTime login = sTimecard.getLogin();
                    LocalTime logout = sTimecard.getLogout();
                    Duration duration = Duration.between(login, logout);
                    hours = (double) duration.getSeconds() / 3600;
                    ttlHours += hours;
                    if (hours > 8.0) {
                        extraHours = hours - 8.0;
                        liquidValue += 8.0 * basicSalary;
                        liquidValue += extraHours * basicSalary * 1.5;
                        liquidValue -= tax - addTax;
                    } else if (hours >= 0.0 && hours <= 8.0) {
                        liquidValue += hours * basicSalary;
                        liquidValue -= tax - addTax;
                    }
                    i++;
                }
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, ttlHours, extraHours, date, tax, addTax,
                    referenceMonth, i);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void generatePaymentHourlyBiWeekly(Hourly sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        Double hours = 0.00, extraHours = 0.00, ttlHours = 0.00, liquidValue = 0.00;
        List<Timecard> getTimeCard = sEmployee.getTimecard();
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
                Double basicSalary = sEmployee.getHourlyValue();
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), oldData, lastPayment);
                }
                int i = 0;
                for (Timecard sTimecard : getTimeCard) {
                    if ((sTimecard.getDate().isBefore(date) || date.isEqual(sTimecard.getDate()))
                            && sTimecard.getDate().isAfter(lastPayment)) {
                        LocalTime login = sTimecard.getLogin();
                        LocalTime logout = sTimecard.getLogout();
                        Duration duration = Duration.between(login, logout);
                        hours = (double) duration.getSeconds() / 3600;
                        ttlHours += hours;
                        if (hours > 8.0) {
                            extraHours = hours - 8.0;
                            liquidValue += 8.0 * basicSalary;
                            liquidValue += extraHours * basicSalary * 1.5;
                            liquidValue -= tax - addTax;
                        } else if (hours >= 0.0 && hours <= 8.0) {
                            liquidValue += hours * basicSalary;
                            liquidValue -= tax - addTax;
                        }
                        i++;
                    }
                }
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, ttlHours, extraHours, date, tax, addTax,
                        referenceMonth, i);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            Double basicSalary = (sEmployee.getHourlyValue());
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
            int i = 0;
            for (Timecard sTimecard : getTimeCard) {
                if (sTimecard.getDate().isBefore(date) || date.isEqual(sTimecard.getDate())) {
                    LocalTime login = sTimecard.getLogin();
                    LocalTime logout = sTimecard.getLogout();
                    Duration duration = Duration.between(login, logout);
                    hours = (double) duration.getSeconds() / 3600;
                    ttlHours += hours;
                    if (hours > 8.0) {
                        extraHours = hours - 8.0;
                        liquidValue += 8.0 * basicSalary;
                        liquidValue += extraHours * basicSalary * 1.5;
                        liquidValue -= tax - addTax;
                    } else if (hours >= 0.0 && hours <= 8.0) {
                        liquidValue += hours * basicSalary;
                        liquidValue -= tax - addTax;
                    }
                    i++;
                }
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, ttlHours, extraHours, date, tax, addTax,
                    referenceMonth, i);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void generatePaymentHourlyWeekly(Hourly sEmployee, LocalDate date, List<Payslip> payslip,
            LocalDate holidays[], int daysHolidayOrWeekn) {
        LocalDate lastPayment, oldData;
        Double hours = 0.00, extraHours = 0.00, ttlHours = 0.00, liquidValue = 0.00;
        List<Timecard> getTimeCard = sEmployee.getTimecard();
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
                Double basicSalary = sEmployee.getHourlyValue();
                double tax = 0.00;
                double addTax = 0.00;
                if (sEmployee.getSyndicate().getActive() == true) {
                    tax = sEmployee.getSyndicate().getTax();
                    addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date, lastPayment);
                }
                int i = 0;
                for (Timecard sTimecard : getTimeCard) {
                    if ((sTimecard.getDate().isBefore(oldData) || oldData.isEqual(sTimecard.getDate()))
                            && sTimecard.getDate().isAfter(lastPayment)) {
                        LocalTime login = sTimecard.getLogin();
                        LocalTime logout = sTimecard.getLogout();
                        Duration duration = Duration.between(login, logout);
                        hours = (double) duration.getSeconds() / 3600;
                        ttlHours += hours;
                        if (hours > 8.0) {
                            extraHours = hours - 8.0;
                            liquidValue += 8.0 * basicSalary;
                            liquidValue += extraHours * basicSalary * 1.5;
                            liquidValue -= tax - addTax;
                        } else if (hours >= 0.0 && hours <= 8.0) {
                            liquidValue += hours * basicSalary;
                            liquidValue -= tax - addTax;
                        }
                        i++;
                    }
                }
                Payslip newPayslip = new Payslip(basicSalary, liquidValue, ttlHours, extraHours, date, tax, addTax,
                        referenceMonth, i);
                sEmployee.getPayslipSheet().add(newPayslip);
            }
        } else {
            if (daysHolidayOrWeekn != 0) {
                oldData = date;
                date = date.plusDays(daysHolidayOrWeekn);
            } else {
                oldData = date;
            }
            String referenceMonth = String.valueOf(oldData.getMonth());
            Double basicSalary = sEmployee.getHourlyValue();
            double tax = 0.00;
            double addTax = 0.00;
            if (sEmployee.getSyndicate().getActive() == true) {
                tax = sEmployee.getSyndicate().getTax();
                addTax = Utils.sumAddFee(sEmployee.getSyndicate().getAdditionalFee(), date);
            }
            int i = 0;
            for (Timecard sTimecard : getTimeCard) {
                if (sTimecard.getDate().isBefore(date) || date.isEqual(sTimecard.getDate())) {
                    LocalTime login = sTimecard.getLogin();
                    LocalTime logout = sTimecard.getLogout();
                    Duration duration = Duration.between(login, logout);
                    hours = (double) duration.getSeconds() / 3600;
                    ttlHours += hours;
                    if (hours > 8.0) {
                        extraHours = hours - 8.0;
                        liquidValue += 8.0 * basicSalary;
                        liquidValue += extraHours * basicSalary * 1.5;
                        liquidValue -= tax - addTax;
                    } else if (hours >= 0.0 && hours <= 8.0) {
                        liquidValue += hours * basicSalary;
                        liquidValue -= tax - addTax;
                    }
                    i++;
                }
            }
            Payslip newPayslip = new Payslip(basicSalary, liquidValue, ttlHours, extraHours, date, tax, addTax,
                    referenceMonth, i);
            sEmployee.getPayslipSheet().add(newPayslip);
        }
    }

    public static void genEmployee(Employee selectedEmployee, String scheduleString, String monthSchedule,
            String daySchedule, String weekSchedule, List<Payslip> payslip, LocalDate date, int daysHolidayOrWeekn,
            LocalDate holidays[]) {
        Hourly sEmployee = (Hourly) selectedEmployee;
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
                    generatePaymentHourlyMonth(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            } else if (daySchedule.equals(dayCurrent)) {
                generatePaymentHourlyMonth(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
            }
        } else {
            weekSchedule = st.nextToken(" ");
            daySchedule = st.nextToken(" ");
            String dayWeek = String.valueOf(daySchedule).toUpperCase();
            String dayWeekCurrent = String.valueOf(date.getDayOfWeek());
            if (weekSchedule.equals("1")) {
                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentHourlyWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            } else {

                if (dayWeekCurrent.equals(dayWeek)) {
                    generatePaymentHourlyBiWeekly(sEmployee, date, payslip, holidays, daysHolidayOrWeekn);
                }
            }
        }
    }
}
