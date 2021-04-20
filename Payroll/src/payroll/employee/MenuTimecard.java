package payroll.employee;

import java.util.List;
import java.util.Scanner;

public class MenuTimecard {
    public static void Timecard(List<Employee> list_employee) {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Timecard ---");
            System.out.println("1 - Timecard login");
            System.out.println("2 - Timecard logout");
            System.out.println("3 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                Login(list_employee);
                break;
            case 2:
                Logout(list_employee);
                break;
            }
        } while (option != 3);
    }

    public static void Login(List<Employee> list_employee) {
        int index = MenuEmployee.findEmployee(list_employee);
        String date, time;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Hourly empl = (Hourly) selectedEmployee;
                System.out.println("Enter day:");
                date = sc.nextLine();
                System.out.println("Enter time login (hh:mm)");
                time = sc.nextLine();
                Timecard tc = new Timecard(date, time);
                empl.getTimecard().add(tc);
            } else {
                System.out.println("Employee is not hourist.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static int findTimecard(List<Timecard> list, String date) {
        int i = 0;
        for (Timecard listE : list) {
            if (listE.getDate().equals(date)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void Logout(List<Employee> list_employee) {
        int index = MenuEmployee.findEmployee(list_employee);
        String date, time;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Hourly empl = (Hourly) selectedEmployee;
                System.out.println("Enter date (DD/MM/YYYY)");
                date = sc.nextLine();
                System.out.println("Enter time logout (hh:mm)");
                time = sc.nextLine();
                int aux = findTimecard(empl.getTimecard(), date);
                if (aux != -1) {
                    Timecard tc = new Timecard(empl.getTimecard().get(aux).getDate(),
                            empl.getTimecard().get(aux).getLogin(), time);
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
