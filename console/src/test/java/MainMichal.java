
import pl.isa.alphateam.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;

import static pl.isa.alphateam.JSONParserReservation.getListOfReservationsFromDatabase;
import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;

public class MainMichal {
    public static void main(String[] args) {
        Address addressM = new Address("Polska", "Łódź", "Hetmańska", 10);

        Boat boatM = new Boat("Falcon", 10, 150, true, 777);

        Customer customerM = new Customer("Michal", "Skoczylas", "1996-01-22",
                "12345678", "12345", addressM, "m.skoczylas1996@gmail.com", "babajaga");

        Reservation reservationM = new Reservation(LocalDate.of(2024, 02, 02),
                LocalDate.of(2024, 02, 05), customerM, boatM);


        // tu musisz przekazywać listę
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservationM);
        saveReservationInDatabase(reservations);


        reservations = getListOfReservationsFromDatabase();
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }


    }}