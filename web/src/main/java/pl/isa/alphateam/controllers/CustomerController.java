package pl.isa.alphateam.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.isa.alphateam.customer.Customer;
import pl.isa.alphateam.reservation.Reservation;
import pl.isa.alphateam.service.BoatReservationService;
import pl.isa.alphateam.service.CustomerService;

import java.util.List;


@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customer-account/{id}/rented-boats")
    public String showRentedBoats(@PathVariable("id") Long id, Model model){
        logger.info("inside showRentedBoats");
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        List<Reservation> reservations = BoatReservationService.getListOfReservationsForCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("reservations", reservations);
        return "customer-account-rented-boats";
    }

}
