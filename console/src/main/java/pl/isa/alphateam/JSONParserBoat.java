package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JSONParserBoat {
    static final String BOAT_JSON_FILE_PATH = "console/src/main/resources/boats.json";

    private JSONParserBoat() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Boat> getListOfBoatsFromDatabase() {
        try {
            byte[] data = Files.readAllBytes(Path.of(BOAT_JSON_FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(data, Boat[].class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static boolean saveBoatInDatabase(List<Boat> boatList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(BOAT_JSON_FILE_PATH), boatList);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
