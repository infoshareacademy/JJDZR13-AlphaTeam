package pl.isa.alphateam;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static final String startDateRequest = "Enter start date: (yyyy-mm-dd): ";
    static final String endDateRequest = "Enter end date: (yyyy-mm-dd): ";

    public static void printMainPanelMenu() {
        System.out.println("""
                                
                MAIN MENU                
                1. Find boats
                2. Login 
                3. Create Account
                4. Exit       
                """);
        int choice = getChosenMenuItem(4);

        switch (choice) {
            case 1 -> printDisplayBoatsMenu();
            case 2 -> printLoginToAccountMenu();
            case 3 -> {
                printCreateAccountMenu();
                printLoginToAccountMenu();
            }
            case 4 -> System.out.println("Good bye");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printDisplayBoatsMenu() {
        System.out.println("""
                 
                FIND BOATS MENU              
                1. List of available boats
                2. Reserve a boat
                3. Return to main panel
                4. Filter list (wip)
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
        //TO DO why surname is not always recorded
        System.out.print("Please provide your last name: >");
        customerData.put("lastName", scanner.next());
        System.out.print("Please provide your birthday date (yyyy-mm-dd)");
        LocalDate birthdayDate = getLocalDateInputFromUser(scanner.next());
        customerData.put("birthdayDate", birthdayDate.toString());
        System.out.print("Please provide your patent reference: >");
        customerData.put("patentNo", scanner.next());
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

    public static void printLoginToAccountMenu() {
        System.out.println("""
                                
                LOGIN MENU                
                1. Login
                2. Return to main panel

                """);
        int choice = getChosenMenuItem(2);

        switch (choice) {
            case 1 -> {
                Customer customer = requestUserToEnterLoginAndPass();
                if (customer == null) {
                    System.out.println("There is no such customer registered");
                    printLoginToAccountMenu();
                } else {
                    printUserAccountMenu(customer);
                }

            }
            case 2 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static void printUserAccountMenu(Customer customer) {
        System.out.println("\nWelcome ***" + customer.getFirstName() + "***");
        System.out.println("""
                                
                USER ACCOUNT MENU                
                1. Update your data (wip)
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

        String date = scanner.next();
        while (!validateLocalDateFromUser(date)) {
            System.out.print("Incorrect choice, try again >");
            date = scanner.next();
        }

        return LocalDate.parse(date);

    }

    public static boolean validateLocalDateFromUser(String choice) {
        String regex = "^((20|19)([0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]))$";
        //TO DO check why 2024-02-03 is not working
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(choice).matches();
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

        return customer;
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
                boolean result = BoatReservationSystem.rentBoatForCustomerReservationCodeRoute(customer, reservation);
                if (result) {
                    printRentalInformation(reservation);
                }
                //TO DO when result = false
                printMainPanelMenu();
            }
            case 2 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    private static void printRentalInformation(Reservation reservation) {
        System.out.println("*".repeat(50));
        System.out.println("Boat " + reservation.getBoat().getName() + ",id: "
                + reservation.getBoat().getBoatId()
                + ", was rented for period from "
                + reservation.getStartDate() + " until: "
                + reservation.getEndDate());
        System.out.println("Rental cost is \n" + reservation.getCostOfReservation());
        System.out.println("*".repeat(50));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    private static void rentABoatWithoutCode(Customer customer) {
        System.out.print("Enter boat id you would like to rent :");
        int boatId = getChosenMenuItem(4);
        scanner.nextLine();

        LocalDate startDate = getLocalDateInputFromUser(startDateRequest);
        LocalDate endDate = getLocalDateInputFromUser(endDateRequest);
        Reservation reservation = BoatReservationSystem.rentBoatForCustomerNoReservationCode(startDate, endDate, customer, boatId);
        printRentalInformation(reservation);
    }

    private static void reserveBoatWithoutLogin() {
        System.out.println("Please enter ID of boat you would like to reserve");
        int boatId = getChosenMenuItem(JSONParserBoat.getListOfBoatsFromDatabase().size() - 1);
        scanner.nextLine();//to clear
        LocalDate startDate = getLocalDateInputFromUser(startDateRequest);
        LocalDate endDate = getLocalDateInputFromUser(endDateRequest);

        while (endDate.isBefore(startDate)) {
            System.out.println("End date is before than start date, try again");
            endDate = getLocalDateInputFromUser(endDateRequest);
        }

        Reservation reservation = BoatReservationSystem.reserveBoatFor24hrs(boatId, startDate, endDate);
        System.out.print("Your reservation code is *" + reservation.getReservationCode() + "* is valid for 24 hrs\n");
        System.out.print("Create/Login to your account in order to rent a boat");
    }

    private static void enterBoatReservationCode(Customer customer) {
        System.out.print("Enter your reservation code: >");
        String reservationCode = scanner.next();
        scanner.nextLine();//to clear whatever
        Reservation reservation = BoatReservationSystem.rentBoatWithReservationCode(reservationCode);

        //TO DO if reservation is null

        System.out.print("Your code is correct, your reserved boat with id "
                + reservation.getBoat().getBoatId()
                + ", "
                + reservation.getBoat().getName()
                + ", capacity: " + reservation.getBoat().getCapacity()
                + ", cost per day " + reservation.getBoat().getCostPerDay());
        confirmBoatRental(customer, reservation);
    }
}
