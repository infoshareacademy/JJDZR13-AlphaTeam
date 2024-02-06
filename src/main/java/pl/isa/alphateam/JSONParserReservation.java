package pl.isa.alphateam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;


import java.io.*;
import java.lang.reflect.Type;
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

/*    public static List<Reservation> getListOfReservationsFromDatabase() {
        try {
            byte[] data = Files.readAllBytes(Path.of(RESERVATION_JSON_FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(data, Reservation[].class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }*/

    public static boolean saveReservationInDatabase(List<Reservation> reservationList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(RESERVATION_JSON_FILE_PATH), reservationList);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static List<Reservation> getListOfReservationsFromDatabase() {

        try {
            InputStream inputStream = new FileInputStream(RESERVATION_JSON_FILE_PATH);
            String json = IOUtils.toString(inputStream);
            Type listType = new TypeToken<ArrayList<Reservation>>() {
            }.getType();
            return new Gson().fromJson(json, listType);
        } catch (IOException e) {
            System.out.println("xxxx");
        }
        return null;
    }





}
