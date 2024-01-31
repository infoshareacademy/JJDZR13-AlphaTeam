import pl.isa.alphateam.Boat;
import pl.isa.alphateam.Menu;

import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;

public class MainBasia {
    public static void main(String[] args) {
        List<Boat> boats = getListOfBoatsFromDatabase();
        for (Boat boat : boats) {
            System.out.println(boat.getName());
        }

        boolean written = saveBoatInDatabase(boats);
        System.out.println(written);

        Menu.printMainPanelMenu();

    }

}


