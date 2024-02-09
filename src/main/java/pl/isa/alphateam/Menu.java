package pl.isa.alphateam;

import org.apache.commons.collections4.CollectionUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

import static pl.isa.alphateam.ReservationUtils.getListOfDaysForPeriod;

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
        LocalDate birthdayDate = getLocalDateInputFromUser("Please provide your birthday date (yyyy-mm-dd) >");
        LocalDate validatedBirthdayDate =validateBirthdayDate18yearsAndFuture(birthdayDate);

        customerData.put("birthdayDate", validatedBirthdayDate.toString());
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
        System.out.print("Please provide your email address (it will be your login): >");

        String email = verifyEmailAddressIfExists(scanner.next());
        customerData.put("emailAddress", email);

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
                4. Print list of your reservations
                5. Logout
                                
                """);
        int choice = getChosenMenuItem(5);

        switch (choice) {
            case 1 -> System.out.println("TO DO");
            case 2 -> {
                Reporting.printListOfAvailableBoats();
                boolean success = rentABoatWithoutCode(customer);
                if (success) {
                    printUserAccountMenu(customer);
                }
            }
            case 3 -> {
                boolean success = enterBoatReservationCode(customer);
                printUserAccountMenu(customer);
            }
            case 4 -> {
                printListOfReservationsForCustomer(customer);
                printUserAccountMenu(customer);
            }
            case 5 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }


    public static void printYesNoMenu() {
        System.out.println("""
                                
                Would like to exit?               
                1. Yes
                2. No
                """);
        int choice = getChosenMenuItem(2);

        switch (choice) {
            case 1 -> printMainPanelMenu();
            case 2 -> System.out.println("ok");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

///////////////////////////////////////////////////////////////////////////////  
//MENU RELATED METHODS
///////////////////////////////////////////////////////////////////////////////

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
        String regex = "^((20|19)([0-9]{2})-(0[1-9]|1[0-2])-((0[1-9])|(1[0-9])|(2[0-9])|(3[0-1])))$";
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

        return CustomerDataCenter.getLoginMap().get(loginDetails);
    }

///////////////////////////////////////////////////////////////////////////////  
//RENTAL RELATED MESSAGES
///////////////////////////////////////////////////////////////////////////////

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
                } else {
                    System.out.println("Reservation code in incorrect");
                }

            }
            case 2 -> printMainPanelMenu();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    private static void printRentalInformation(Reservation reservation) {

        String lineBoatDetails = "Boat " + reservation.getBoat().getName() + ",id:" + reservation.getBoat().getBoatId();
        String lineRentalPeriod = "was rented for period from " + reservation.getStartDate() + " until: " + reservation.getEndDate();
        String lineCost = "Rental cost is :" + reservation.getCostOfReservation();
        System.out.println("*".repeat(60));
        System.out.println("*" + lineBoatDetails);
        System.out.println("*" + lineRentalPeriod);
        System.out.println("*" + lineCost);
        System.out.println("*".repeat(60));
    }

    private static void printListOfReservationsForCustomer(Customer customer) {
        List<Reservation> listOfResForCustomer = BoatReservationSystem.getListOfReservationsForCustomer(customer);
        if (listOfResForCustomer.isEmpty()) {
            System.out.println("Currently there are not reservations for the " + customer.getFirstName() + " " + customer.getLastName());
        } else {
            for (Reservation reservation : listOfResForCustomer) {
                printRentalInformation(reservation);
            }
        }

    }



///////////////////////////////////////////////////////////////////////////////  
//BOAT RESERVATIONS AND RENTALS
///////////////////////////////////////////////////////////////////////////////

    private static boolean rentABoatWithoutCode(Customer customer) {
        System.out.print("Enter boat id you would like to rent :");
        int boatId = getChosenMenuItem(JSONParserBoat.getListOfBoatsFromDatabase().size());
        scanner.nextLine();

        getDaysNAforBoatId(boatId);

        LocalDate startDate = getStartDateAndValidateAgainstDaysAvailableForBoat(boatId);
        LocalDate endDate = getLocalDateInputFromUser(endDateRequest);

        endDate = getValidatedEndDateIncludingPeriodValidation(boatId, startDate, endDate);

        Reservation reservation = BoatReservationSystem.rentBoatForCustomer(startDate, endDate, customer, boatId);
        printRentalInformation(reservation);
        return true;
    }

    private static void reserveBoatWithoutLogin() {
        System.out.println("Enter boat id you would like to rent :");
        int boatId = getChosenMenuItem(JSONParserBoat.getListOfBoatsFromDatabase().size());
        scanner.nextLine();//to clear

        getDaysNAforBoatId(boatId);

        LocalDate startDate = getStartDateAndValidateAgainstDaysAvailableForBoat(boatId);
        LocalDate endDate = getLocalDateInputFromUser(endDateRequest);

        endDate = getValidatedEndDateIncludingPeriodValidation(boatId, startDate, endDate);

        Reservation reservation = BoatReservationSystem.reserveBoatFor24hrs(boatId, startDate, endDate);
        System.out.print("\nYour reservation code is * " + reservation.getReservationCode() + " * and is valid for 24 hrs\n");
        System.out.print("Create/Login to your account in order to rent a boat");
    }

    private static boolean enterBoatReservationCode(Customer customer) {
        System.out.print("Enter your reservation code: >");
        String reservationCode = scanner.next();
        scanner.nextLine();//to clear whatever
        Reservation reservation = BoatReservationSystem.rentBoatWithReservationCode(reservationCode);

        if (reservation == null) {
            System.out.println("Incorrect reservation code");
            return false;
        } else {
            System.out.print("Your code is correct, your reserved boat with id "
                    + reservation.getBoat().getBoatId()
                    + ", "
                    + reservation.getBoat().getName()
                    + ", capacity: " + reservation.getBoat().getCapacity()
                    + ", cost per day " + reservation.getBoat().getCostPerDay());
            confirmBoatRental(customer, reservation);
            BoatReservationSystem.rentBoatForCustomer(reservation.getStartDate(), reservation.getEndDate(), customer, reservation.getBoat().getBoatId());
            return true;
        }

    }

///////////////////////////////////////////////////////////////////////////////
//LOCAL DATES CHECKS AND VALIDATIONS
///////////////////////////////////////////////////////////////////////////////

    private static LocalDate getValidatedEndDateIncludingPeriodValidation(int boatId, LocalDate startDate, LocalDate endDate) {
        endDate = getEndDateAferStartDate(startDate, endDate);

        List<LocalDate> datesForBoat = getListOfDaysForPeriod(startDate, endDate);
        validateIfSelectedPeriodIsAvailableForRent(datesForBoat, BoatReservationSystem.getListOFDatesNAforBoatId(boatId));
        return endDate;
    }

    private static LocalDate getEndDateAferStartDate(LocalDate startDate, LocalDate endDate) {
        while (endDate.isBefore(startDate)) {
            System.out.println("End date is before than start date, try again");
            endDate = getLocalDateInputFromUser(endDateRequest);
        }
        return endDate;
    }

    private static void validateIfSelectedPeriodIsAvailableForRent(List<LocalDate> datesForBoat, List<LocalDate> listOFDatesNAforBoatId) {
        LocalDate startDate = datesForBoat.get(0);
        LocalDate endDate = datesForBoat.get(datesForBoat.size() - 1);

        while (CollectionUtils.containsAny(datesForBoat, listOFDatesNAforBoatId)) {
            System.out.println("Boat for period from " + startDate.toString() + " until " + endDate.toString() + " is not available for rental");
            printYesNoMenu();
            endDate = getLocalDateInputFromUser(endDateRequest);
            endDate = getEndDateAferStartDate(startDate, endDate);
            datesForBoat = getListOfDaysForPeriod(startDate, endDate);
        }
        System.out.println("Boat for period from " + startDate.toString() + " until " + endDate.toString() + " is available for rental");
    }

    private static LocalDate getStartDateAndValidateAgainstDaysAvailableForBoat(int boatId) {

        LocalDate startDate = getLocalDateInputFromUser(startDateRequest);

        while (checkIfBoatRentedOnThatDay(boatId, startDate)) {
            System.out.println("Boat is not available on that day");
            startDate = getLocalDateInputFromUser(startDateRequest);
        }
        return startDate;
    }

    private static void getDaysNAforBoatId(int boatId) {
        List<LocalDate> dates = BoatReservationSystem.getListOFDatesNAforBoatId(boatId);
        if (!dates.isEmpty()) {
            System.out.println("Currently this boat is no available on ");
            dates.stream().map(d -> d.toString() + ", ").forEach(System.out::print);
            System.out.println();
        }
    }

    private static boolean checkIfBoatRentedOnThatDay(int boatId, LocalDate date) {
        List<LocalDate> dates = BoatReservationSystem.getListOFDatesNAforBoatId(boatId);
        return dates.contains(date);
    }


    private static LocalDate validateBirthdayDate18yearsAndFuture(LocalDate birthdayDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate date18YearsBack = currentDate.minusYears(18);
        while (birthdayDate.isAfter(date18YearsBack)) {
            if (birthdayDate.isAfter(currentDate)) {
                System.out.println("This is future date");
            } else {
                System.out.println("Sorry, you must be aged 18 or above to rent a boat");
            }
            printYesNoMenu();
            birthdayDate = getLocalDateInputFromUser("Please provide your birthday date (yyyy-mm-dd) >");

        }
        return birthdayDate;
    }


///////////////////////////////////////////////////////////////////////////////
//EMAIL VERIFICATION    
///////////////////////////////////////////////////////////////////////////////
    private static String verifyEmailAddressIfExists(String email) {
        boolean ifEmailExist = CustomerDataCenter.checkIfEmailExists(email);
        while (ifEmailExist) {
            System.out.print("This login already exists, try again >");
            email = scanner.next();
            ifEmailExist = CustomerDataCenter.checkIfEmailExists(email);
        }
        return email;
    }
}
