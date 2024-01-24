import pl.isa.alphateam.Address;
import pl.isa.alphateam.Boat;
import pl.isa.alphateam.Customer;
import pl.isa.alphateam.Reservation;

import java.time.LocalDate;
import java.util.UUID;

public class MainMichal {
    public static void main(String[] args) {

        Address basiaAddress = new Address("KochanyKraj", "City", "Ulica", 12);
        Customer basia = new Customer("Basia", "LName", "2024/01/20", "1234", basiaAddress, "basia@gmail.com", "pass");
        Boat boatForBasia = new Boat("BoatName", 5, 100.5, true, "b1");
        Reservation reservation1 = new Reservation(LocalDate.of(2024,1,4),
                LocalDate.of(2024,1,11),
                basia, boatForBasia);


        System.out.println(basia);
        double cost =reservation1.getCostOfReservation();
        System.out.println(cost);

        String uuid = reservation1.getUUID();




    }
}
