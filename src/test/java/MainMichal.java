import pl.isa.alphateam.Boat;

import java.time.LocalDate;
import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;

public class MainMichal {
    public static void main(String[] args) {
        List<Boat> boats = getListOfBoatsFromDatabase();
        boats.forEach(System.out::println);
        saveBoatInDatabase(boats);
    }
}
