package pl.isa.alphateam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;

public class CreateReservation {

    public static List<Reservation> getReservations() {
        return JSONParserReservation.getListOfReservationsFromDatabase();
    }

    public static void addReservation(Reservation reservation) {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        JSONParserReservation.saveReservationInDatabase(reservations);
    }

    public static Reservation makeReservation(){
        Scanner scanner = new Scanner(System.in);


        // Tworzenie klienta, łodzi i rezerwacji
        Address addressM = new Address("Polska", "Łódź", "Hetmańska", 10);

        Boat boatMajkel = new Boat("Fujimo", 12, 130.5, true, 4466 );



        Customer customerMajkel = new Customer("Majkel", "Skoczylas", "1996-01-22",
                "1234756", "123599", addressM, "m.skoczylas1996@gmail.com", "papajaga");


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
                    Reservation reservation = new Reservation(startDate, endDate, customerMajkel, boat);
                    System.out.println("Reservation added: " + reservation.getStartDate() + " - " +
                            reservation.getEndDate() +
                            " for customer: " + reservation.getCustomer().getFirstName() + " for boat: " +
                            reservation.getBoat().getName());
                }
            }

            if (!reservationSuccessful) {
                System.out.println("Invalid boatId or the boat is not available. Please try again.");

            }

        }


       return new Reservation(startDate, endDate, customerMajkel, boatMajkel);
    }
    }

