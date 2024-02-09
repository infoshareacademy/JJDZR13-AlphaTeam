import pl.isa.alphateam.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;
import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;
import static pl.isa.alphateam.JSONParserReservation.getListOfReservationsFromDatabase;
import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;


public class MainBasia {
    public static void main(String[] args) throws IOException {
/*        List<Boat> boats = getListOfBoatsFromDatabase();
        for (Boat boat : boats) {
            System.out.println(boat.getName());
        }

        boolean written = saveBoatInDatabase(boats);
        System.out.println(written);*/

     //   Menu.printMainPanelMenu();
//////////////////////////
    /*    List<Customer> customers = new ArrayList<>();
        customers= JSONParserCustomer.getCustomers1();
        List<Customer> customers2 = JSONParserCustomer.getListOfCustomersFromDatabase();

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        for (Customer customer : customers2) {
            System.out.println(customer);
        }

        Customer kasia = new Customer("Hope", "Z.", "23/10/11", "1254", new Address("Kraj","Miasto","Ulica",13), "kasie@email.com", "kasiaPass");
        customers.add(kasia);
        JSONParserCustomer.saveCustomer(customers);*/

      //  CustomerDataCenter.getLoginMap();
/////////////////////////////


        Map<Map<String, String>, Customer> customerLoginDetails = new HashMap<>();
        var customersList = JSONParserCustomer.customersList;

        for (Customer customer : customersList) {
            System.out.println(customer);
 /*           Map<String, String> login = new HashMap<>();
            login.put(customer.getFirstName(), customer.getPassword());
            customerLoginDetails.put(login, customer);*/
        }

        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", "pass");


        /////////////////////////////
/*        Customer kasia = new Customer("Hope", "Z.", "23/10/11", "1254", new Address("Kraj","Miasto","Ulica",13), "kasie@email.com", "kasiaPass");

        List<Reservation> reservationList = JSONParserReservation.getListOfReservationsFromDatabase();
        for (Reservation reservation : reservationList) {
            System.out.println(reservation);
        }
        JSONParserReservation.saveReservationInDatabase(reservationList);*/
        LocalDate currentDate = LocalDate.now();
        LocalDate date18YearsBack = currentDate.minusYears(18);
        System.out.println(date18YearsBack);

        Address addressM = new Address("Polska", "Łódź", "Hetmańska", 10);
        Boat boatM = new Boat("Banana Boat", 12, 251.2, true, 2);
        Customer customerM = new Customer("Imie", "Skoczylas", "1996-01-22",
                "123456", addressM, "m.skoczylas1996@gmail.com", "babajaga");
        String startdate = "2024-01-01";
        String endDate = "2024-01-15";

        Reservation reservationM = new Reservation(LocalDate.parse(startdate),
                LocalDate.parse(endDate), customerM, boatM);


       // var reservations =getListOfReservationsFromDatabase();
        var reservations= getListOfReservationsFromDatabase().stream().collect(toCollection(ArrayList::new));
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
        System.out.println("----");
        reservations.add(reservationM);
        saveReservationInDatabase(reservations);
        reservations= new ArrayList<>(getListOfReservationsFromDatabase());
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

    }

}


