package pl.isa.alphateam;

import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;


public class Reporting {

    public static void printListOfAvailableBoats() {
        List<Boat> boats = getListOfBoatsFromDatabase();
        int i = 0;

        for (Boat boat:boats) {
            if (boat.getIsAvailable()) {
                System.out.printf("id: %d Boat: name= %s, capacity= %d, costPerDay= %f %n", boat.getBoatId(),
                        boat.getName(),boat.getCapacity(), boat.getCostPerDay());
                i++;
            }
        }

    }


}
