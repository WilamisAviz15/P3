package payroll.employee.utils;

import java.util.Scanner;

public class Utils {
    public static String consoleMenuTimecard(String tmp, Scanner sc) {
        while (true) {
            tmp = sc.nextLine();
            try {
                int id_int = Integer.parseInt(tmp);
                if (id_int > 0 && id_int < 4) {
                    break;
                } else {
                    System.out.print("Value cannot be less than 0 or bigger than 3! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }

    public static String consoleTimecard(String tmp, Scanner sc, boolean isTime) {
        while (true) {
            tmp = sc.nextLine();
            try {
                int id_int = Integer.parseInt(tmp);
                if (!isTime) {
                    if (id_int > 0) {
                        break;
                    } else {
                        System.out.print("Value cannot be less or equal 0! Try again.\n");
                    }
                } else {
                    if (id_int >= 0) {
                        break;
                    } else {
                        System.out.print("Value cannot be less than 0! Try again.\n");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }
}
