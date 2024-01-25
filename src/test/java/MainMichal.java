import org.json.simple.parser.ParseException;
import pl.isa.alphateam.Boat;

import java.io.IOException;

import static pl.isa.alphateam.JSONParser.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParser.saveBoatInDatabase;

public class MainMichal {
    public static void main(String[] args) throws IOException, ParseException {
        getListOfBoatsFromDatabase();
        Boat boatForBasia = new Boat("MichalBoat", 4, 12000000.5, true, "Alpha teeam");
        saveBoatInDatabase(boatForBasia);
    }
}
