package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;

import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;

public class BoatReservationSystem {
    private static final Map<String, Reservation> reservationCodeMap= new HashMap<>();
   private static final List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        Address addressM = new Address("Polska", "Lodz", "Hetmanska", 10);
        Customer customer = new Customer("MichalNNN", "Skoczylas", "1996-01-22",
                "123456", "1235", addressM, "m.skoczylas1996@gmail.com", "babajaga");


        // Wprowadzanie danych przez użytkownika

       LocalDate startDate = Menu.getLocalDateInputFromUser( "Select start date: (RRRR-MM-DD): ");
       LocalDate endDate = Menu.getLocalDateInputFromUser("Select end date: (RRRR-MM-DD): ");


        Reporting.printListOfAvailableBoats();


        boolean reservationSuccessful = false;

        while (!reservationSuccessful) {
            System.out.println("Enter the ID of the boat you want to reserve: ");
            int boatId = scanner.nextInt();
            for (Boat boat : getListOfBoatsFromDatabase()) {
                if (boat.getBoatId() == boatId && boat.getIsAvailable()) {
                    boat.setAvailable(false);
                    System.out.println("Reservation successful! You have reserved " + boat.getName());
                    reservationSuccessful = true;
                    Reservation reservation = new Reservation(startDate, endDate, customer, boat);
                    System.out.println("Rezerwacja dodana: " + reservation.getStartDate() + " - " +
                            reservation.getEndDate() +
                            " dla klienta " + reservation.getCustomer().getFirstName() + " na łódź " +
                            reservation.getBoat().getName());

                    List<Reservation> reservations = new ArrayList<>();
                    reservations.add(reservation);
                    saveReservationInDatabase(reservations);
                    break;
                }
            }

            if (!reservationSuccessful) {
                System.out.println("Invalid boatId or the boat is not available. Please try again.");
            }
        }
    }


    public static Reservation reserveBoatFor24hrs(boolean isLoggedIn, int boatID, LocalDate startDate, LocalDate endDate) {
        Boat boat = getListOfBoatsFromDatabase().get(boatID);
        Reservation reservation = new Reservation(startDate, endDate, null, boat);

        if (!isLoggedIn) {
            String reservationCode = reservation.getReservationCode();
            reservationCodeMap.put(reservationCode, reservation);
            return reservation;
        }
        return null;
    }
  /*  public boolean rentABoatWithReservationCode(String reservationCode) {
        return reservationCode.equalsIgnoreCase();
    }
*/

    public static Reservation rentBoatWithReservationCode(String reservationCode) {
        return reservationCodeMap.get(reservationCode);
    }

    public static String rentBoatForCustomerReservatioCodeRoute(Customer customer, Reservation reservation ) {
        reservation.setCustomer(customer);
        return "Boat was successfully rented";
    }

    public static Reservation rentBoatForCustomerNoReservationCode(LocalDate startDate, LocalDate endDate, Customer customer, int boatID) {
        List<Boat> boats = getListOfBoatsFromDatabase();
        Boat boat = boats.stream().filter(b -> b.getBoatId() == boatID).collect(Collectors.toList()).get(0);
        Reservation reservation = new Reservation(startDate, endDate, customer, boat);
        reservations.add(reservation);
        saveReservationInDatabase(reservations);

        return reservation;
    }
}












