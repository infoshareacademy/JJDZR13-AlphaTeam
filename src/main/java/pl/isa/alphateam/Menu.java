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
            case 2 -> System.out.println("TO DO");
            case 3 -> printMainPanelMenu();
            case 4 -> System.out.println("TO DO");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printCreateAccountMenu() {
        System.out.println("""
                
                1. Create Account
                2. Save Changes
                3. Cancel Creation
                """);

        //TO DO

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
                
                1. Enter user name
                2. Enter your password

                """);
        //TO DO
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
