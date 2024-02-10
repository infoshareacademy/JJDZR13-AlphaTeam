package pl.isa.alphateam;

import java.util.Scanner;
import java.util.regex.Pattern;


public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static Reporting reporting = new Reporting();

    public static void printMainPanelMenu() {
        System.out.println("""
                
                1. Find boats
                2. Login
                3. Create Account
                4. Exit        
                """);
        int choice =getChosenMenuItem(4);

        switch (choice) {
            case 1 -> printDisplayBoatsMenu();
            case 2 -> printLoginToAccountMenu();
            case 3 -> printCreateAccountMenu();
            case 4 -> System.out.println("Good bye");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printDisplayBoatsMenu() {
        System.out.println("""
                
                1. List of available boats
                2. Reserve a boat
                3. Return to main panel
                4. Filter list
                """);

        int choice =getChosenMenuItem(4);

        switch (choice) {
            case 1 -> {
                reporting.printListOfAvailableBoats();
                printDisplayBoatsMenu();
            }
            case 2 -> createReservation();
            case 3 -> printMainPanelMenu();
            case 4 -> System.out.println("TO DO");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printCreateAccountMenu() {
        System.out.println("""
                
                1. Create Account
                2. Cancel Creation
                """);

        int choice = getChosenMenuItem(2);

        switch (choice) {
            case 1 -> createAccount();
            case 2 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
    private static void createAccount() {
        Customer newCustomer = CreateAccount.createCustomer();
        CreateAccount.addCustomer(newCustomer);
        System.out.println("Account created successfully!");
        printRegisterFormMenu();
    }

    private static void createReservation() {
        Reservation newReservation = CreateReservation.makeReservation();
        CreateReservation.addReservation(newReservation);
        printMainPanelMenu();
    }

    public static void printRegisterFormMenu() {
        System.out.println("""
                
                1. Rent a boat
                2. Manage your rental
                3. Edit account
                4. Log out
                """);
        //TO DO

    }


    public static void printLoginToAccountMenu() {
        System.out.println("""
                
                1. Enter e-mail
                2. Enter your password

                """);
        String emailAddress, password;
        System.out.println("Enter e-mail: ");
        emailAddress = scanner.next();
        System.out.println("Enter login: ");
        password = scanner.next();

        if(login(emailAddress, password)) {
            System.out.println("Login successful!");
            printRegisterFormMenu();
        }
    }
    private static boolean login(String emailAddress, String password) {
        for (Customer customer : JSONParserCustomer.getListOfCustomerFromDatabase()) {
            if (customer.getEmailAddress().equals(emailAddress) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        System.out.println("Invalid e-mail or password. Please try again.");
        return false;
    }

    public static int getChosenMenuItem(int itemsInMenu) {
        System.out.println("Please provide your choice >");
        String choice = scanner.next();

        while (!validateMenuChoice(choice, itemsInMenu)) {
            System.out.println("Incorrect choice, try again >");
            choice = scanner.next();
        }
        return Integer.parseInt(choice);
    }

    public static boolean validateMenuChoice(String choice, int limit) {
        if (choice == null) {
            return false;
        }
        String regex = "^[1-" + limit + "]{1}+$";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(choice).matches();
    }
}
