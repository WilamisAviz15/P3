package payroll.payment;

import java.util.Scanner;

public class MenuPayoutSchedule {
    public static void Menu() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("Payout Schedule");
        System.out.println("1 - List All Payment Schedules");
        System.out.println("2 - List Payment Schedules by ID");
        System.out.println("3 - Back");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        }
    }
}
