package pl.isa.alphateam;

import java.util.List;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;


public class Reporting {

    public void printListOfAvailableBoats() {
        List<Boat> boats = getListOfBoatsFromDatabase();
        for (Boat boat:boats) {
            if (boat.getIsAvailable()) {
                System.out.printf("Boat: name= %s, capacity= %d, costPerDay= %f, boatId= %s %n",
                        boat.getName(),boat.getCapacity(), boat.getCostPerDay(), boat.getBoatId());
            }
        }

    }


}
