package payroll.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Salaried;
import payroll.employee.model.Timecard;
import payroll.payment.model.CheckByPostOffice;
import payroll.payment.model.DepositByBankAccount;
import payroll.payment.model.HandsCheck;
import payroll.payment.model.PaymentMethod;
import payroll.syndicate.Syndicate;

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

    public static String consoleReadInputIntegerOptions(String tmp, Scanner op, int min, int max) {
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

    public static String consoleReadInputIntegerNumber(String tmp, Scanner op) {
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

    public static String consoleReadInputIntegerWithOR(String tmp, Scanner sc, int var1, int var2) {
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

    public static List<Employee> cloneList(List<Employee> list) {
        List<Employee> clone = new ArrayList<Employee>();
        Employee aux = null;
        for (Employee item : list) {
            if (item instanceof Salaried) {
                Salaried newItem = (Salaried) item;
                aux = new Salaried(newItem);
            } else if (item instanceof Commissioned) {
                Commissioned newItem = (Commissioned) item;
                aux = new Commissioned(newItem);
            } else if (item instanceof Hourly) {
                Hourly newItem = (Hourly) item;
                aux = new Hourly(newItem);
            }
            clone.add(aux);
        }
        return clone;
    }

    public static List<Timecard> cloneListTimecard(List<Timecard> list) {
        List<Timecard> clone = new ArrayList<Timecard>();
        Timecard aux = null;
        for (Timecard item : list) {
            Timecard newItem = (Timecard) item;
            aux = new Timecard(newItem);
            clone.add(aux);
        }
        return clone;
    }

    public static PaymentMethod cloneListPaymentMethod(PaymentMethod list) {
        PaymentMethod aux = null;

        if (list instanceof CheckByPostOffice) {
            CheckByPostOffice newItem = (CheckByPostOffice) list;
            aux = new CheckByPostOffice(newItem);
        } else if (list instanceof DepositByBankAccount) {
            DepositByBankAccount newItem = (DepositByBankAccount) list;
            aux = new DepositByBankAccount(newItem);
        } else if (list instanceof HandsCheck) {
            HandsCheck newItem = (HandsCheck) list;
            aux = new HandsCheck(newItem);
        }

        return aux;
    }

    public static Syndicate cloneListSyndicate(Syndicate list) {
        Syndicate aux = null;
        Syndicate newItem = (Syndicate) list;
        aux = new Syndicate(newItem);
        return aux;
    }

    public static String consoleReadInputIntegerSpecial(String tmp, Scanner sc, int var) {
        while (true) {
            try {
                tmp = sc.nextLine();
                if (tmp.equals("$")) {
                    break;
                } else {
                    int number = Integer.parseInt(tmp);
                    if (number > var) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        return tmp;
    }

    public static String consoleReadInputDouble(String tmp, Scanner sc) {
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
