package payroll.employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Timecard;
import payroll.utils.Utils;

public class MenuTimecard {
    public static void Timecard(List<Employee> list_employee, Stack<List<Employee>> undo) {
        int option;
        String tmp = "";
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Timecard ---");
            System.out.println("1 - Timecard login");
            System.out.println("2 - Timecard logout");
            System.out.println("3 - Back");
            tmp = Utils.consoleMenuTimecard(tmp, op);
            option = Integer.parseInt(tmp);
            switch (option) {
            case 1:
                Login(list_employee, undo);
                break;
            case 2:
                Logout(list_employee, undo);
                break;
            }
        } while (option != 3);
    }

    public static void Login(List<Employee> list_employee, Stack<List<Employee>> undo) {
        int index = MenuEmployee.findEmployee(list_employee);
        LocalTime loginTime;
        LocalDate date;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            undo.push(Utils.cloneList(list_employee));
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Hourly empl = (Hourly) selectedEmployee;
                List<Timecard> t = Utils.cloneListTimecard(empl.getTimecard());
                empl.setTimecard(t);
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
                System.out.println("Enter time login (hh):");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, true);
                int logInH = Integer.parseInt(tmp);
                System.out.println("Enter time login (mm):");
                tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, true);
                int logInM = Integer.parseInt(tmp);
                loginTime = LocalTime.of(logInH, logInM);
                Timecard tc = new Timecard(date, loginTime);
                empl.getTimecard().add(tc);
            } else {
                System.out.println("Employee is not hourist.");
            }
        } else {
            System.out.println("Employee not found.");
        }

    }

    public static int findTimecard(List<Timecard> list, LocalDate date) {
        int i = 0;
        for (Timecard listE : list) {
            if (listE.getDate().equals(date)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void Logout(List<Employee> list_employee, Stack<List<Employee>> undo) {
        int index = MenuEmployee.findEmployee(list_employee);
        LocalTime loginTime;
        LocalDate date;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Hourly empl = (Hourly) selectedEmployee;
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
                int aux = findTimecard(empl.getTimecard(), date);
                if (aux != -1) {
                    undo.push(Utils.cloneList(list_employee));
                    List<Timecard> t = Utils.cloneListTimecard(empl.getTimecard());
                    empl.setTimecard(t);
                    System.out.println("Enter time login (hh):");
                    tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, true);
                    int logInH = Integer.parseInt(tmp);
                    System.out.println("Enter time login (mm):");
                    tmp = Utils.consoleReadInputIntegerNumber(tmp, sc, true);
                    int logInM = Integer.parseInt(tmp);
                    loginTime = LocalTime.of(logInH, logInM);
                    Timecard tc = new Timecard(empl.getTimecard().get(aux).getDate(),
                            empl.getTimecard().get(aux).getLogin(), loginTime);
                    empl.getTimecard().set(aux, tc);
                } else {
                    System.out.println("You need to login first.");
                }
            } else {
                System.out.println("Employee is not hourist.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}