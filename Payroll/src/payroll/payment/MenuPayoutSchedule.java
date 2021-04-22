package payroll.payment;

import java.util.Scanner;

import payroll.payment.model.PayoutSchedule;
import payroll.utils.Utils;

public class MenuPayoutSchedule {

    public static void printAllSchedules(PayoutSchedule paySchedule) {
        System.out.println("--- Avaliable schedules ---");
        for (String pS : paySchedule.getTypesSchedule()) {
            System.out.println("- " + pS);
        }
        System.out.println("-----------------------");
    }

    public static void createSchedule(PayoutSchedule paySchedule, Scanner sc) {
        System.out.println("Enter name from payment schedule");
        paySchedule.getTypesSchedule().add(sc.nextLine());
    }

    public static void Menu(PayoutSchedule paySchedule) {
        int option;
        String tmp = "";
        do {
            Scanner op = new Scanner(System.in);
            System.out.println("Payout Schedule");
            System.out.println("1 - List All Payment Schedules");
            System.out.println("2 - Add new Payment Schedule");
            System.out.println("3 - Back");
            tmp = Utils.consoleReadInputIntegerOptions(tmp, op, 1, 4);
            option = Integer.parseInt(tmp);
            switch (option) {
            case 1:
                printAllSchedules(paySchedule);
                break;
            case 2:
                createSchedule(paySchedule, op);
                break;
            }
        } while (option != 3);
    }
}
