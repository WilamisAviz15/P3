package payroll.payment;

import java.util.Scanner;

import payroll.payment.model.PayoutSchedule;

public class MenuPayoutSchedule {

    public static void printAllSchedules(PayoutSchedule paySchedule){
        System.out.println("--- Avaliable schedules ---");
        for (String pS : paySchedule.getTypesSchedule()) {
            System.out.println("- " +pS);
        }
        System.out.println("-----------------------");
    }

    public static void Menu(PayoutSchedule paySchedule) {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Payout Schedule");
        System.out.println("1 - List All Payment Schedules");
        System.out.println("2 - Add new Payment Schedule");
        System.out.println("3 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            printAllSchedules(paySchedule);
            break;
        case 2:
            break;
        }
    }
}
