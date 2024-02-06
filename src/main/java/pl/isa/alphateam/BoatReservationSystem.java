package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;

import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;

public class BoatReservationSystem {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        // Tworzenie klienta, łodzi i rezerwacji
        Address addressM = new Address("Polska", "Łódź", "Hetmańska", 10);
////
//        Boat boat = new Boat("FalconGGGG", 10, 150, true, 7778);
        Customer customer = new Customer("MichalNNN", "Skoczylas", "1996-01-22",
                "123456", "1235", addressM, "m.skoczylas1996@gmail.com", "babajaga");


        // Wprowadzanie danych przez użytkownika
        LocalDate startDate = null;
        while (startDate == null) {
            System.out.print("Select start date: (RRRR-MM-DD): ");
            String startDateStr = scanner.nextLine();
            try {
                startDate = LocalDate.parse(startDateStr);
            } catch (Exception e) {
                System.out.print("Wrong format ");
            }
        }

        LocalDate endDate = null;
        while (endDate == null) {
            System.out.print("Select end date: (RRRR-MM-DD): ");
            String endDateStr = scanner.nextLine();
            try {
                endDate = LocalDate.parse(endDateStr);
            } catch (Exception e) {
                System.out.print("Wrong format ");
            }
        }


        System.out.println("This is the available boats list: ");
        for (Boat boat : getListOfBoatsFromDatabase()) {
            System.out.println("ID: " + boat.getBoatId() + ", Name: " +
                    boat.getName() + ", Available: " + boat.getIsAvailable());
        }


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

}












