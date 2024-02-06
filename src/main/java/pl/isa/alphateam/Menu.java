package pl.isa.alphateam;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class Menu {
    static Scanner scanner = new Scanner(System.in);


    public static void printMainPanelMenu() {
        System.out.println("""
                                
                1. Find boats
                2. Login / create new account
                3. Create Account
                4. Exit        
                """);
        int choice = getChosenMenuItem(4);

        switch (choice) {
            case 1 -> printDisplayBoatsMenu();
            case 2 -> printLoginToAccountMenu();
            case 3 -> {
                printCreateAccountMenu();
                printRegisterFormMenu();
            }
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

        int choice = getChosenMenuItem(4);

        switch (choice) {
            case 1 -> {
                Reporting.printListOfAvailableBoats();
                printDisplayBoatsMenu();
            }
            case 2 -> {
                Reporting.printListOfAvailableBoats();
                reserveBoatWithoutLogin();
                printMainPanelMenu();
            }
            case 3 -> printMainPanelMenu();
            case 4 -> System.out.println("TO DO");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printCreateAccountMenu() {
        Map<String, String> customerData = new HashMap<>();
        System.out.print("Please provide your first name: >");
        customerData.put("firstName", scanner.next());
        System.out.print("Please provide your last name: >");
        customerData.put("lastName", scanner.next());
        System.out.print("Please provide your birthday date: >");
        customerData.put("birthdayDate", scanner.next());
        System.out.print("Please provide your address - country  >");
        customerData.put("country", scanner.next());
        System.out.print("Please provide your address - city  >");
        customerData.put("city", scanner.next());
        System.out.print("Please provide your address - street name  >");
        customerData.put("streetName", scanner.next());
        System.out.print("Please provide your address - street number  >");
        customerData.put("streetNumber", scanner.next());
        System.out.print("Please provide your email address: >");
        customerData.put("emailAddress", scanner.next());
        System.out.print("Please provide your password: >");
        customerData.put("password", scanner.next());

        CustomerDataCenter.createNewCustomerRecord(customerData);
        System.out.println("Account was created");

    }

    public static void printRegisterFormMenu() {
        System.out.println("""
                                
                1. Rent a boat
                2. Manage your rental
                3. Edit account
                4. Log out
                """);
        int choice = getChosenMenuItem(4);

        switch (choice) {
            case 1 -> System.out.println("TO DO");
            case 2 -> System.out.println("TO DO");
            case 3 -> System.out.println("TO DO");
            case 4 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }


    public static void printLoginToAccountMenu() {
        System.out.println("""
                                
                1. Login
                2. Create new Account
                3. Return to main panel

                """);
        int choice = getChosenMenuItem(3);

        switch (choice) {
            case 1 -> {
               Customer customer = requestUserToEnterLoginAndPass();
                printUserAccountMenu(customer);
            }
            case 2 -> printCreateAccountMenu();
            case 3 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printUserAccountMenu(Customer customer) {
        System.out.println("\nWelcome ***" + customer.getFirstName() + "***");
        System.out.println("""
                                
                1. Update your data
                2. Rent a boat without reservation code
                3. Rent a boat with reservation code
                4. Logout

                """);
        int choice = getChosenMenuItem(4);

        switch (choice) {
            case 1 -> System.out.println("TO DO");
            case 2 -> {
                Reporting.printListOfAvailableBoats();
                rentABoatWithoutCode(customer);
            }
            case 3 -> enterBoatReservationCode(customer);
            case 4 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printReservationSystemMenu() {
        System.out.println("""
                                
                1. Reserve a boat for 24 hrs
                2. Login to your account and rent a boat 
                3, Go back to main menu
                """);

        int choice = getChosenMenuItem(3);

        switch (choice) {
            case 1 -> {
                Reporting.printListOfAvailableBoats();
                printDisplayBoatsMenu();
            }
            case 2 -> printLoginToAccountMenu();
            case 3 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int getChosenMenuItem(int itemsInMenu) {
        System.out.print("Please provide your choice >");
        String choice = scanner.next();

        while (!validateMenuChoice(choice, itemsInMenu)) {
            System.out.print("Incorrect choice, try again >");
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

    public static LocalDate getLocalDateInputFromUser(String message) {
        System.out.print(message);
        String startDateStr = scanner.nextLine();
        return LocalDate.parse(startDateStr);

    }

    public static Customer requestUserToEnterLoginAndPass() {
        System.out.print("Enter your login (email address) >");
        String login = scanner.next();
        System.out.print("Enter your password >");
        String password = scanner.next();
        return getCustomerAssociatedToLoginDetails(login, password);

    }

    private static Customer getCustomerAssociatedToLoginDetails(String login, String password) {
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put(login, password);
        Customer customer = CustomerDataCenter.getLoginMap().get(loginDetails);
        if (customer == null) {
            System.out.println("There is no such customer registered");
        } else {
            return customer;
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public static void confirmBoatRental(Customer customer, Reservation reservation) {
        System.out.println("""
                                
                1. Confirm your reservation
                2. Quit reservation and return to main menu
                """);

        int choice = getChosenMenuItem(2);

        switch (choice) {
            case 1 -> {
                String message = BoatReservationSystem.rentBoatForCustomerReservatioCodeRoute(customer, reservation);
                System.out.println(message);
            }
            case 2 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    private static void enterBoatReservationCode(Customer customer) {
        System.out.println("Enter your reservation code now");
        String reservationCode = scanner.next();

        Reservation reservation = BoatReservationSystem.rentBoatWithReservationCode(reservationCode);
//TO DO if reservation is null
        System.out.println("Your code is correct, your reserved boat has id " + reservation.getBoat());
        confirmBoatRental(customer, reservation);
    }

    private static void rentABoatWithoutCode(Customer customer) {
        System.out.print("Enter boat id you would like to rent :");
        int boatId = getChosenMenuItem(4);
        scanner.nextLine();
        LocalDate startDate = getLocalDateInputFromUser("Select start date: (RRRR-MM-DD): ");
        LocalDate endDate = getLocalDateInputFromUser("Select end date: (RRRR-MM-DD): ");
        Reservation reservation = BoatReservationSystem.rentBoatForCustomerNoReservationCode(startDate, endDate, customer, boatId);
        printRentalInformation(reservation);
    }

    private static void printRentalInformation(Reservation reservation) {
        System.out.println("*".repeat(50));
        System.out.println("Boat " + reservation.getBoat().getName() + ",id: "
                + reservation.getBoat().getBoatId()
                + ", was rented for period from "
                + reservation.getStartDate() + " untill: "
                + reservation.getEndDate());
        System.out.println("Rental cost is \n" + reservation.getCostOfReservation());
        System.out.println("*".repeat(50));
    }


    private static void reserveBoatWithoutLogin() {
        System.out.println("Please enter ID of boat you would like to reserve");
        int boatId = getChosenMenuItem(4);
        LocalDate startDate = getLocalDateInputFromUser("Select start date: (RRRR-MM-DD): ");
        LocalDate endDate = getLocalDateInputFromUser("Select end date: (RRRR-MM-DD): ");
        Reservation reservation = BoatReservationSystem.reserveBoatFor24hrs(false, boatId, startDate, endDate);
        System.out.print("Your reservation code is *" + reservation.getReservationCode() + "* is valid for 24 hrs\n");
        System.out.print("Create/Login to your account in order to rent a boat");
    }
}
