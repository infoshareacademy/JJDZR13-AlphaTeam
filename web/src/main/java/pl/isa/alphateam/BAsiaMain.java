package pl.isa.alphateam;


import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.boat.BoatDto;
import pl.isa.alphateam.customer.Customer;
import pl.isa.alphateam.customer.Login;
import pl.isa.alphateam.parser.JSONParserBoat;
import pl.isa.alphateam.parser.JSONParserCustomer;
import pl.isa.alphateam.parser.JSONParserReservation;
import pl.isa.alphateam.reservation.Reservation;
import pl.isa.alphateam.service.BoatReservationService;
import pl.isa.alphateam.service.BoatService;
import pl.isa.alphateam.service.BoatServiceImpl;
import pl.isa.alphateam.service.CustomerService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BAsiaMain {


    public static void main(String[] args) throws IOException {
//        var customers = JSONParserCustomer.getCustomers1();
//        System.out.println(customers);
/*
        Login login = new Login();
        login.setEmail("zosia@email.com");
        login.setPassword("zosiapass");
        Customer customer = CustomerService.getCustomerWithGivenLogin(login);
        System.out.println("Searched customer "+customer);
        */


 /*      Customer customer = CustomerService.getCustomerWithGivenId(2);
        System.out.println(customer);
        var list = BoatReservationService.getListOfReservationsForCustomer(customer);
        System.out.println(list);*/


        List<Reservation> reservationList = JSONParserReservation.getListOfReservationsFromDatabase();
        System.out.println(reservationList);


/*        BoatServiceImpl boatServiceImpl = new BoatServiceImpl();
        LocalDate from = LocalDate.parse("2024-02-01");
        LocalDate to = LocalDate.parse("2024-05-31");*/

/*        var boatlist =boatServiceImpl.findAllAvailableBoats(from, to);
        for (BoatDto boat : boatlist) {
            System.out.println(boat.getBoatId()+" "+boat.getNotAvailableDates());
        }*/
    }

}
