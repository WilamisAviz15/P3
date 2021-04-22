package payroll.utils;

import java.util.Scanner;

public class Utils {
    public static String consoleMenuTimecard(String tmp, Scanner sc) {
        while (true) {
            tmp = sc.nextLine();
            try {
                int number = Integer.parseInt(tmp);
                if (number > 0 && number < 4) {
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

    public static String consoleReadInputIntegerNumber(String tmp, Scanner sc, boolean isIncludedZero) {
        while (true) {
            tmp = sc.nextLine();
            try {
                int number = Integer.parseInt(tmp);
                if (!isIncludedZero) {
                    if (number > 0) {
                        break;
                    } else {
                        System.out.print("Value cannot be less or equal 0! Try again.\n");
                    }
                } else {
                    if (number >= 0) {
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

    public static String consoleReadInputIntegerOptions(String tmp, Scanner op, int min, int max){
        while (true) {
            tmp = op.nextLine();
            try {
                int tmp_op = Integer.parseInt(tmp);
                if (tmp_op >= min && tmp_op < max) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Try again..\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }

    public static String consoleReadInputIntegerNumber(String tmp, Scanner op){
        while (true) {
            tmp = op.nextLine();
            try {
                int number = Integer.parseInt(tmp);
                if (number > 0) {
                    break;
                } else {
                    System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }

    public static String consoleReadInputIntegerWithOR(String tmp, Scanner sc, int var1, int var2){
        while (true) {
            try {
                tmp = sc.nextLine();
                int number = Integer.parseInt(tmp);
                if (number == var1 || number == var2) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }

    public static String consoleReadInputDouble(String tmp, Scanner sc){
        while (true) {
            tmp = sc.nextLine();
            try {
                Double number = Double.parseDouble(tmp);
                if (number > 0.00) {
                    break;
                } else {
                    System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }
}
