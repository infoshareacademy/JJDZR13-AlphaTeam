package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JSONParserReservation {

    static final String RESERVATION_JSON_FILE_PATH = "console/src/main/resources/reservations.json";

    private JSONParserReservation() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Reservation> getListOfReservationsFromDatabase() {
        try {
            byte[] data = Files.readAllBytes(Path.of(RESERVATION_JSON_FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(data, Reservation[].class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static boolean saveReservationInDatabase(List<Reservation> reservationList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(RESERVATION_JSON_FILE_PATH), reservationList);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static List<Reservation> getListOfReservationsFromFakeDatabase(){
    Address addressM = new Address("Polska", "Łódź", "Hetmańska", 10);
    Boat boatM = new Boat("Banana Boat", 12, 251.2, true, 2);
    Customer customerM = new Customer("Michal", "Skoczylas", "1996-01-22","123456789",
            "123456", addressM, "m.skoczylas1996@gmail.com", "babajaga");
    String startdate = "2024-01-01";
    String endDate = "2024-01-15";

    Reservation reservationM = new Reservation(LocalDate.parse(startdate),
            LocalDate.parse(endDate), customerM, boatM);

        Address addressN = new Address("Polska", "Łódź", "Hetmańska", 10);
        Boat boatN = new Boat("Cruise Ship", 1200, 36541.2, true, 3);
        Customer customerN = new Customer("Michal", "Skoczylas", "1996-01-22","123456789",
                "123456", addressN, "m.skoczylas1996@gmail.com", "babajaga");
        String startdateN = "2024-01-01";
        String endDateN = "2024-01-31";

        Reservation reservationN = new Reservation(LocalDate.parse(startdateN),
                LocalDate.parse(endDateN), customerN, boatN);

        Address addressB = new Address("Polska", "Miaso", "Ulica", 10);
        Boat boatB = new Boat("Cable Ferry", 25, 21.3, true, 4);
        Customer customerB = new Customer("Basia", "Name", "1996-01-22","123456789",
                "123456", addressB, "emailBasia", "passBasia");
        String startdateB = "2024-03-01";
        String endDateB = "2024-03-15";

        Reservation reservationB = new Reservation(LocalDate.parse(startdateB),
                LocalDate.parse(endDateB), customerB, boatB);

        List<Reservation> reservationsList = new ArrayList<>();
        reservationsList.add(reservationM);
        reservationsList.add(reservationN);
        reservationsList.add(reservationB);
        return reservationsList;
    }
}
