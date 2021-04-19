package payroll;

import java.util.Scanner;
import payroll.employee.Commissioned;
import payroll.employee.Employee;
import payroll.employee.Hourly;
import payroll.employee.Salaried;
import payroll.employee.Sales;
import payroll.employee.Timecard;
import payroll.payment.DepositByBankAccount;
import payroll.payment.CheckByPostOffice;
import payroll.payment.HandsCheck;
import payroll.payment.PaymentMethod;
import payroll.syndicate.AdditionalFee;
import payroll.syndicate.Syndicate;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    public int id = 20210000; // será incrementado a cada novo funcionario;
    public String idSyndicate = "";
    public int idSyndicateInt = 0;
    public List<Employee> list_employee = new ArrayList<Employee>();
    private String[] accountType = { "Corrente", "Poupança", "Fácil", "Conjunta" };

    public void Employees() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("--- Employees ---");
            System.out.println("1 - Add Employees");
            System.out.println("2 - Remove Employees");
            System.out.println("3 - Edit Employees");
            System.out.println("4 - List All Employees");
            System.out.println("5 - Search Employees");
            System.out.println("6 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                addEmployee();
                break;
            case 2:
                removeEmployee();
                break;
            case 3:
                editEmployee();
                break;
            case 4:
                ListAllEmployee();
                break;
            case 5:
                listEmployeeById();
                break;
            }
        } while (option != 6);
    }

    public void addEmployee() {
        PaymentMethod paymentMethod = null;
        Syndicate sindicalist = null;
        Employee newEmployees;
        String bankId;
        String agency;
        String accountNumber;
        Double value;
        String type_employeeS;
        Double salary = 0.00;
        Scanner sc = new Scanner(System.in);
        int optionPaymentMethod, optionSindicalist;
        String tmp;
        System.out.println("Type the name:");
        String name = sc.nextLine();
        System.out.println("Type the adrress:");
        String address = sc.nextLine();
        System.out.println("Type of employee (0 - hourly, 1 - salaried, 2 - commissioned)");
        while (true) {
            type_employeeS = sc.nextLine();
            try {
                int type_employee_int = Integer.parseInt(type_employeeS);
                if (type_employee_int >= 0 && type_employee_int < 3) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        int type_employee = Integer.parseInt(type_employeeS);
        if (type_employee == 0) {
            System.out.println("Type the hourly wage:");
            while (true) {
                tmp = sc.nextLine();
                try {
                    Double salary_double = Double.parseDouble(tmp);
                    if (salary_double > 0.00) {
                        break;
                    } else {
                        System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            salary = Double.parseDouble(tmp);
            newEmployees = new Hourly(++id, name, address, paymentMethod, salary, sindicalist);
        } else if (type_employee == 1) {
            System.out.println("Type the salary:");
            while (true) {
                tmp = sc.nextLine();
                try {
                    Double salary_double = Double.parseDouble(tmp);
                    if (salary_double > 0.00) {
                        break;
                    } else {
                        System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            salary = Double.parseDouble(tmp);
            newEmployees = new Salaried(++id, name, address, paymentMethod, salary, sindicalist);
        } else if (type_employee == 2) {
            double percentage;
            System.out.println("Type the salary:");
            while (true) {
                tmp = sc.nextLine();
                try {
                    Double salary_double = Double.parseDouble(tmp);
                    if (salary_double > 0.00) {
                        break;
                    } else {
                        System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            salary = Double.parseDouble(tmp);
            System.out.println("Type the % to comisson:");
            while (true) {
                tmp = sc.nextLine();
                try {
                    Double percentage_Double = Double.parseDouble(tmp);
                    if (percentage_Double > 0) {
                        break;
                    } else {
                        System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            percentage = Double.parseDouble(tmp);
            newEmployees = new Commissioned(++id, name, address, paymentMethod, salary, percentage, sindicalist);
        } else {
            newEmployees = new Employee(++id, name, address, paymentMethod, sindicalist);
        }
        System.out.println("Type Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
        while (true) {
            try {
                tmp = sc.nextLine();
                int type_payment_int = Integer.parseInt(tmp);
                if (type_payment_int >= 0 && type_payment_int < 3) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        optionPaymentMethod = Integer.parseInt(tmp);
        System.out.println("Type the bank ID:");
        bankId = sc.nextLine();
        System.out.println("Type agency number:");
        agency = sc.nextLine();
        System.out.println("Type account number:");
        accountNumber = sc.nextLine();
        if (optionPaymentMethod == 0) {
            int numberCheck;
            System.out.println("Type number Check:");
            while (true) {
                try {
                    tmp = sc.nextLine();
                    int type_payment_int = Integer.parseInt(tmp);
                    if (type_payment_int > 0) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            numberCheck = Integer.parseInt(tmp);
            paymentMethod = new CheckByPostOffice(bankId, agency, accountNumber, numberCheck, address);
        } else if (optionPaymentMethod == 1) {
            int numberCheck;
            System.out.println("Type number Check:");
            while (true) {
                try {
                    tmp = sc.nextLine();
                    int type_payment_int = Integer.parseInt(tmp);
                    if (type_payment_int > 0) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            numberCheck = Integer.parseInt(tmp);
            paymentMethod = new HandsCheck(bankId, agency, accountNumber, numberCheck);
        } else if (optionPaymentMethod == 2) {
            int index;
            System.out.println("Select type of account:");
            System.out.println("0 - " + accountType[0]);
            System.out.println("1 - " + accountType[1]);
            System.out.println("2 - " + accountType[2]);
            System.out.println("3 - " + accountType[3]);
            while (true) {
                try {
                    tmp = sc.nextLine();
                    int optionPaymentMethod_int = Integer.parseInt(tmp);
                    if (optionPaymentMethod_int >= 0 && optionPaymentMethod_int < 4) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            index = Integer.parseInt(tmp);
            paymentMethod = new DepositByBankAccount(bankId, agency, accountNumber, accountType[index]);
        }
        newEmployees.setPaymentMethod(paymentMethod);

        System.out.printf("%s will be part of the Syndicate? 1- yes | 2- no\n", newEmployees.getName());
        while (true) {
            try {
                tmp = sc.nextLine();
                int optionSindicalist_int = Integer.parseInt(tmp);
                if (optionSindicalist_int == 1 || optionSindicalist_int == 2) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        optionSindicalist = Integer.parseInt(tmp);
        if (optionSindicalist == 1) {
            Double tax;
            System.out.println("Type the monthly TAX:");
            while (true) {
                tmp = sc.nextLine();
                try {
                    Double tax_Double = Double.parseDouble(tmp);
                    if (tax_Double > 0.00) {
                        break;
                    } else {
                        System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            tax = Double.parseDouble(tmp);
            String aux = Character.toString(name.charAt(0));
            sindicalist = new Syndicate(aux + 2021 + "S000" + (++idSyndicateInt), tax, true);
            newEmployees.setSyndicate(sindicalist);
        } else {
            sindicalist = new Syndicate("0", 0.00, false);
            newEmployees.setSyndicate(sindicalist);
        }
        list_employee.add(newEmployees);
        System.out.println("Successful registration.");
        System.out.println("ID employee is: \n" + id);
    }

    public int findEmployee() {
        int id;
        int aux = 0;
        String tmp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        while (true) {
            tmp = sc.nextLine();
            try {
                int id_int = Integer.parseInt(tmp);
                if (id_int > 0) {
                    break;
                } else {
                    System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        id = Integer.parseInt(tmp);
        for (Employee listE : list_employee) {
            if (listE.getId() == id) {
                return aux;
            }
            aux++;
        }
        return -1;
    }

    public boolean findEmployeeSyndicate(String renameSyndicalId) {
        for (Employee listE : list_employee) {
            if (listE.getSyndicate().getIdSyndicate().equals(renameSyndicalId)) {
                return true;
            }
        }
        return false;
    }

    public void removeEmployee() {
        int index = findEmployee();
        if (index != -1) {
            list_employee.remove(index);
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void LaunchSales() {
        int index = findEmployee();
        Double value;
        String date;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Commissioned) {
                Commissioned empl = (Commissioned) selectedEmployee;
                System.out.println("Enter sale value");
                value = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter sale date (DD/MM/YYYY)");
                date = sc.nextLine();
                Sales sl = new Sales(date, value);
                empl.getSales().add(sl);
            } else {
                System.out.println("Employee is not comissioned.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void ListAllEmployee() {
        System.out.println("___________________________________________");
        for (Employee listE : list_employee) {
            System.out.println(listE);
            System.out.println("___________________________________________");
        }
    }

    public void listEmployeeById() {
        int id;
        String tmp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        while (true) {
            tmp = sc.nextLine();
            try {
                int id_int = Integer.parseInt(tmp);
                if (id_int > 0) {
                    break;
                } else {
                    System.out.print("Value cannot be less than or equal to 0! Try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }
        }
        id = Integer.parseInt(tmp);
        System.out.println("___________________________");
        for (Employee listE : list_employee) {
            if (listE.getId() == id) {
                System.out.println(listE);
            }
        }
        System.out.println("___________________________");
    }

    public void editEmployee() { //TODO: Try catch;
        String id, op, attr;
        Double salary = 0.00;
        String bankId;
        String agency;
        String accountNumber;
        Double value;
        Scanner sc = new Scanner(System.in);
        int index = findEmployee();
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            System.out.printf("Employed selected: %s. What do you want to edit?\n", selectedEmployee.getName());
            System.out.println("0 - Name");
            System.out.println("1 - Address");
            System.out.println("2 - Type of employee");
            System.out.println("3 - Paymento Method");
            System.out.println("4 - Join/leave syndicate");
            System.out.println("5 - Monthly syndicate fee");
            System.out.println("6 - Syndical Identification");
            op = sc.nextLine();
            if (op.equals("0")) {
                System.out.println("Type the new name");
                attr = sc.nextLine();
                selectedEmployee.setName(attr);
                System.out.println("Successful changes.");
            } else if (op.equals("1")) {
                System.out.println("Type the new address");
                attr = sc.nextLine();
                selectedEmployee.setAddress(attr);
                System.out.println("Successful changes.");
            } else if (op.equals("2")) {
                // int oldAttr = list_employee.get(index).getType_employee();
                System.out.println("Type the new type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
                attr = sc.nextLine();
                if (attr.equals("0")) {
                    System.out.println("Type the hourly wage:");
                    salary = sc.nextDouble();
                    selectedEmployee = new Hourly(selectedEmployee.getId(), selectedEmployee.getName(),
                            selectedEmployee.getAddress(), selectedEmployee.getPaymentMethod(), salary,
                            selectedEmployee.getSyndicate());
                } else if (attr.equals("1")) {
                    System.out.println("Type the salary:");
                    salary = sc.nextDouble();
                    selectedEmployee = new Salaried(selectedEmployee.getId(), selectedEmployee.getName(),
                            selectedEmployee.getAddress(), selectedEmployee.getPaymentMethod(), salary,
                            selectedEmployee.getSyndicate());
                } else if (attr.equals("2")) {
                    Double percentage;
                    System.out.println("Type the salary:");
                    salary = sc.nextDouble();
                    System.out.println("Type the % to comisson:");
                    percentage = sc.nextDouble();
                    selectedEmployee = new Commissioned(selectedEmployee.getId(), selectedEmployee.getName(),
                            selectedEmployee.getAddress(), selectedEmployee.getPaymentMethod(), salary, percentage,
                            selectedEmployee.getSyndicate());
                }
                list_employee.set(index, selectedEmployee);
                System.out.println("Successful changes.");
            } else if (op.equals("3")) {
                System.out.println(
                        "Select the new Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
                // if(selectedEmployee.getPaymentMethod() instanceof CheckByPostOffice){} //V
                // DEPOIS
                attr = sc.nextLine();
                System.out.println("Type the bank ID:");
                bankId = sc.nextLine();
                System.out.println("Type agency number:");
                agency = sc.nextLine();
                System.out.println("Type account number:");
                accountNumber = sc.nextLine();
                if (attr.equals("0")) {
                    int numberCheck;
                    System.out.println("Type number Check:");
                    numberCheck = sc.nextInt();
                    selectedEmployee.setPaymentMethod(new CheckByPostOffice(bankId, agency, accountNumber,
                            numberCheck, selectedEmployee.getAddress()));
                } else if (attr.equals("1")) {
                    int numberCheck;
                    System.out.println("Type number Check:");
                    numberCheck = sc.nextInt();
                    selectedEmployee
                            .setPaymentMethod(new HandsCheck(bankId, agency, accountNumber, numberCheck));
                } else if (attr.equals("2")) {
                    int idx;
                    System.out.println("Select type of account:");
                    System.out.println("0 - " + accountType[0]);
                    System.out.println("1 - " + accountType[1]);
                    System.out.println("2 - " + accountType[2]);
                    System.out.println("3 - " + accountType[3]);
                    idx = sc.nextInt();
                    selectedEmployee.setPaymentMethod(
                            new DepositByBankAccount(bankId, agency, accountNumber, accountType[idx]));
                }
                System.out.println("Successful changes.");
            } else if (op.equals("4")) {
                int answer;
                if (selectedEmployee.getSyndicate().getActive() == true) {
                    System.out.println(
                            selectedEmployee.getName() + " already belongs to syndicate. Do you want to leave?");
                    System.out.println("0 - No");
                    System.out.println("1 - Yes");
                    answer = sc.nextInt();
                    if (answer == 1) {
                        selectedEmployee.getSyndicate().setActive(false);
                    }
                } else {
                    System.out.println(selectedEmployee.getName()
                            + " does not belongs to syndicate or is inactive. Do you want to join?");
                    System.out.println("0 - No");
                    System.out.println("1 - Yes");
                    answer = sc.nextInt();
                    if (answer == 1) {
                        Double tax;
                        System.out.println("Type the monthly TAX:");
                        tax = sc.nextDouble();
                        String aux = Character.toString(selectedEmployee.getName().charAt(0));
                        selectedEmployee.getSyndicate().setIdSyndicate(aux + 2021 + "S000" + (++idSyndicateInt));
                        selectedEmployee.getSyndicate().setTax(tax);
                        selectedEmployee.getSyndicate().setActive(true);
                    }
                }
                System.out.println("Successful changes.");
            } else if (op.equals("5")) {
                if (selectedEmployee.getSyndicate().getActive() == true) {
                    Double tax;
                    System.out.println("Type the new monthly TAX:");
                    tax = sc.nextDouble();
                    selectedEmployee.getSyndicate().setTax(tax);
                    System.out.println("Successful changes.");
                } else {
                    System.out.println(selectedEmployee.getName() + " does not belongs to syndicate to edit fee");
                    ;
                }
            } else if (op.equals("6")) {
                if (selectedEmployee.getSyndicate().getActive() == true) {
                    String nameID;
                    System.out.println("Enter the new Identification");
                    nameID = sc.nextLine();
                    if (!findEmployeeSyndicate(nameID)) {
                        selectedEmployee.getSyndicate().setIdSyndicate(nameID);
                        System.out.println("Successful changes.");
                    } else {
                        System.out.println("This name already exists. Please choose another one.");
                    }
                } else {
                    System.out.println(selectedEmployee.getName() + " does not belongs to syndicate to edit ID");
                    ;
                }
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void Login() {
        int index = findEmployee();
        String date, time;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Hourly empl = (Hourly) selectedEmployee;
                System.out.println("Enter date (DD/MM/YYYY)");
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

    public int findTimecard(List<Timecard> list, String date) {
        int i = 0;
        for (Timecard listE : list) {
            if (listE.getDate().equals(date)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void Logout() {
        int index = findEmployee();
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

    public void LaunchFee() {
        int index = findEmployee();
        String date;
        Double value;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee.getSyndicate().getActive() == true) {
                System.out.println("Enter date (DD/MM/YYYY)");
                date = sc.nextLine();
                System.out.println("Enter value");
                value = sc.nextDouble();
                AdditionalFee aF = new AdditionalFee(date, value);
                selectedEmployee.getSyndicate().getAdditionalFee().add(aF);
            } else {
                System.out.println(selectedEmployee.getName()
                        + " does not belongs to syndicate or is inactive to add service fee.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void Timecard() {
        int option;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("Timecard");
            System.out.println("1 - Timecard login");
            System.out.println("2 - Timecard logout");
            System.out.println("3 - Back");
            option = op.nextInt();
            switch (option) {
            case 1:
                Login();
                break;
            case 2:
                Logout();
                break;
            }
        } while (option != 3);
    }

    public void rotatePayroll() {

    }

    public void payoutSchedule() {
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

    public void run() {
        String option;
        int opc;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("===============PAYROLL===============");
            System.out.println("1  - Employees");
            System.out.println("2  - Timecard");
            System.out.println("3  - Launch Sales");
            System.out.println("4  - Launch Service Fee");
            System.out.println("5  - Rotate Payroll");
            System.out.println("6  - Payout Schedule");
            System.out.println("7  - Undo");
            System.out.println("8  - Redo");
            System.out.println("0 - Exit");
            System.out.println("================================================");
            while (true) {
                option = op.nextLine();
                try {
                    int option_int = Integer.parseInt(option);
                    if (option_int >= 0 && option_int < 9) {
                        break;
                    } else {
                        System.out.print("INVALID OPTION! Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only numbers!");
                }
            }
            opc = Integer.parseInt(option);
            switch (opc) {
            case 1:
                Employees();
                break;
            case 2:
                Timecard();
                break;
            case 3:
                LaunchSales();
                break;
            case 4:
                LaunchFee();
                break;
            case 5:
                rotatePayroll();
                break;
            case 6:
                payoutSchedule();
                break;
            case 7:

                break;
            case 8:

                break;
            case 0:
                System.exit(0);
                break;
            }
        } while (opc != 0);
    }
}