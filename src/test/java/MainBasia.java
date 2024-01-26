import org.json.simple.parser.ParseException;
import pl.isa.alphateam.Boat;

import java.io.IOException;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;

//https://javacodepoint.com/jackson-json-serialization-and-deserialization/
//problem with jackson "mvn clean install -U" that helped with dependency
public class MainBasia {
    public static void main(String[] args) throws IOException, ParseException {
  /*      Address basiaAddress = new Address("KochanyKraj", "City", "Ulica", 12);
        Customer basia = new Customer("Basia", "LName", "2024/01/20", "1234", basiaAddress, "basia@gmail.com", "pass");
        Boat boatForBasia = new Boat("BoatName", 5, 100.5, true, "b1");
        Reservation reservation1 = new Reservation(LocalDate.of(2024,1,4),
                LocalDate.of(2024,1,11),
                basia, boatForBasia);


        System.out.println(basia);
        double cost =reservation1.getCostOfReservation();
        System.out.println(cost);*/
        //Json PArser ->JSON simple

        getListOfBoatsFromDatabase();
        Boat boatForBasia = new Boat("Wonder", 4, 12000000.5, true, "Alpha teeam");
        saveBoatInDatabase(boatForBasia);

    }

}


