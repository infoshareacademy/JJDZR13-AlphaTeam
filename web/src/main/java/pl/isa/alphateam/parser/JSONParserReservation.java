package pl.isa.alphateam.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.isa.alphateam.reservation.Reservation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JSONParserReservation {

    static final String RESERVATION_JSON_FILE_PATH = "web/src/main/resources/reservations.json";

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
}
