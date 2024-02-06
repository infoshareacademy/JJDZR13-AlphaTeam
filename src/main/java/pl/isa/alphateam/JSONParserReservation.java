package pl.isa.alphateam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.BOAT_JSON_FILE_PATH;

public class JSONParserReservation {

    static final String RESERVATION_JSON_FILE_PATH = "src/main/resources/reservations.json";

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
