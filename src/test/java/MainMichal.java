
import pl.isa.alphateam.Address;
import pl.isa.alphateam.Boat;
import pl.isa.alphateam.Customer;
import pl.isa.alphateam.Reservation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;

import static pl.isa.alphateam.JSONParserReservation.getListOfReservationsFromDatabase;
import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;

public class MainMichal {
    public static void main(String[] args) {

        Address addressM = new Address("Polska", "Łódź", "Hetmańska", 10);

        Boat boatM = new Boat("Falcon", 10, 150, true, "777");

        Customer customerM = new Customer("Michal", "Skoczylas", "1996-01-22",
                "123456", addressM, "m.skoczylas1996@gmail.com", "babajaga");

//        Reservation reservationM = new Reservation(LocalDate.of(2024, 02, 02),
//                LocalDate.of(2024, 02, 05), customerM, boatM);

        Reservation reservationM = new Reservation("2024-02-02", "2024-02-05", customerM, boatM);













       // saveReservationInDatabase(reservationM);



//        List<Boat> boats = getListOfBoatsFromDatabase();
//        for (Boat boat : boats) {
//            System.out.println(boat);
//        }
//
//        List<Reservation> reservations = getListOfReservationsFromDatabase();
//        for (Reservation reservation : reservations) {
//            System.out.println(reservation);
//        }
//
//
//        boolean written = saveReservationInDatabase(reservations);
//        System.out.println(written);
////
//        boolean written2 = saveBoatInDatabase(boats);
//        System.out.println(written2);


    }
}
