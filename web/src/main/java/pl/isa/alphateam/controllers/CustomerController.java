package pl.isa.alphateam.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.isa.alphateam.customer.Customer;
import pl.isa.alphateam.reservation.Reservation;
import pl.isa.alphateam.service.BoatReservationService;
import pl.isa.alphateam.service.CustomerService;


import java.util.List;



@Controller
public class CustomerController {
   // private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

   /* @GetMapping("/customer-account/{id}/rented-boats")
    public String showRentedBoats(@PathVariable("id") Long id, Model model) {
        logger.info("[showRentedBoats]");
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        List<Reservation> reservations = BoatReservationService.getListOfReservationsForCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("reservations", reservations);
        return "customer-account-rented-boats";
    }*/

/*    @GetMapping("/customer-account-reservation/{id}")
    public String enterReservationCodeForm(@PathVariable("id") Long id,
                                           Model model) {
        logger.info("[enterReservationCodeForm] GetMapping ");
        String reservationCode = "";
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        model.addAttribute("reservationCode", reservationCode);
        model.addAttribute("customer", customer);
        return "customer-account-reservation-form";

    }

    @PostMapping(value = "/customer-account-reservation/{id}")
    public String enterReservationCode(@PathVariable("id") Long id,
                                       @Valid @ModelAttribute("reservationCode") String reservationCode,
                                       BindingResult result,
                                       Model model) {
        logger.info("[enterReservationCode] PostMapping :"+reservationCode);
         Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        if (result.hasErrors()) {
            model.addAttribute("reservationCode", reservationCode);
            return "customer-account-reservation-form";
        } else {
            logger.info("[enterReservationCode] "+reservationCode);
            Reservation reservationWithCode = BoatReservationService.rentBoatWithReservationCode(reservationCode);
            BoatReservationService.rentBoatForCustomer(reservationWithCode.getStartDate(), reservationWithCode.getEndDate(), customer, reservationWithCode.getBoat().getBoatId());

            model.addAttribute("customer", customer);
            model.addAttribute("reservation", reservationWithCode);
            model.addAttribute("boat", reservationWithCode.getBoat());

            return "boat-rented";
        }
    }*/



}
