package pl.isa.alphateam.controllers;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.boat.BoatDto;
import pl.isa.alphateam.customer.Customer;
import pl.isa.alphateam.customer.CustomerDto;
import pl.isa.alphateam.customer.Login;
import pl.isa.alphateam.parser.JSONParserBoat;
import pl.isa.alphateam.reservation.FilterDates;
import pl.isa.alphateam.reservation.Reservation;
import pl.isa.alphateam.reservation.ReservationCode;
import pl.isa.alphateam.service.BoatReservationService;
import pl.isa.alphateam.service.BoatService;
import pl.isa.alphateam.service.BoatServiceImpl;
import pl.isa.alphateam.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

import static pl.isa.alphateam.boat.BoatMapper.mapToBoat;
import static pl.isa.alphateam.service.BoatReservationService.getBoatBasedOnBoatID;
import static pl.isa.alphateam.service.BoatReservationService.rentBoatForCustomer;

@Controller
public class BoatController {
    private static final Logger logger = LoggerFactory.getLogger(BoatController.class);

    private final BoatService boatService;

    @Autowired
    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping({"/", "/menu"})
    public String index(Model model) {
        logger.info("inside index");
        return "menu";
    }

    @GetMapping("/boats")
    public String getListOfBoats(Model model) {
        logger.info("inside boats");
        List<Boat> boats = boatService.findAll();
        model.addAttribute("boats", boats);
        return "list-of-boats";
    }

    @GetMapping("/boats/add")
    public String showAddBoatForm(Model model) {
        model.addAttribute("boat", new Boat());
        return "add-boatForm";
    }

    @PostMapping("/boats/add")
    public String addBoat(@Valid @ModelAttribute("boat") Boat boat,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            return "add-boatForm";
        }
        boatService.save(boat);
        return "redirect:/boats";
    }

     @GetMapping("/boats-parser")
    public String getListOfBoatsParser(Model model) {
        logger.info("inside boats-parser");
        LocalDate startDate = LocalDate.parse("2002-01-01");
        LocalDate endDate = LocalDate.parse("2002-01-01");

        List<BoatDto> boatList = boatService.findAllAvailableBoats(startDate, endDate);
        // List<Boat> boatList = JSONParserBoat.getListOfBoatsFromDatabase();
        model.addAttribute("boats", boatList);
        return "list-of-boats";
    }

 /* @PostMapping(value = "/boats-parser")
  public String filterDates(@Valid @ModelAttribute("filterDates") FilterDates filterDates,
                           BindingResult result,
                           Model model) {

      if (result.hasErrors()) {
          model.addAttribute("filterDate", filterDates);
          return "list-of-boats";
      } else {
          logger.info("ïnside fitlerDates");
          //CustomerService.createNewCustomerRecord(customerDto);
          return "list-of-boats";
      }
  }*/
/////////////////////////////////////////////////////////////
// [Reservation -code] List of boats -> get available boats//
/////////////////////////////////////////////////////////////

    @GetMapping("/reservation-code-filter-boats")
    public String filterBoatsFormRegistrationCode(Model model) {
        logger.info("inside reservation code-filter-boats");
        FilterDates filterDates = new FilterDates();
        model.addAttribute("filterDates", filterDates);
        return "reservation-code-find-boat-filter-form";
    }

    @PostMapping(value = "/reservation-code-filter-boats")
    public String collectDateFromFormReservationCode(
            @Valid @ModelAttribute("filterDates") FilterDates filterDates,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("filterDates", filterDates);
            return "reservation-code-find-boat-filter-form";
        } else if (filterDates.getStartDate().isAfter(filterDates.getEndDate())) {
            model.addAttribute("filterDates", filterDates);
            return "reservation-code-find-boat-filter-form";
        } else {
            logger.info("inside collectDateFromFormReservationCode");
            List<BoatDto> boatList = boatService.findAllAvailableBoats(filterDates.getStartDate(), filterDates.getEndDate());
            model.addAttribute("boats", mapToBoat(boatList));
            return "reservation-code-list-of-boats-available";
        }
    }

    //--------------------------------------------------------------------
    @GetMapping("/reservation-code/boatId/{boatId}/from/{startDate}/to/{endDate}")
    public String getReservationCodeForABoat(@PathVariable Long boatId,
                                             @PathVariable LocalDate startDate,
                                             @PathVariable LocalDate endDate,
                                             Model model) {
        logger.info("inside reservation code/boatId - getReservationCodeForABoat");
        Reservation reservationWithCode = BoatReservationService.reserveBoatFor24hrs(Math.toIntExact(boatId), startDate, endDate);
        logger.info("[getReservationCodeForABoat] GetMapping Reservation code: " + reservationWithCode.getReservationCode());
        model.addAttribute("reservation", reservationWithCode);
        logger.info("[getReservationCodeForABoat] GetMapping boat id: " + reservationWithCode.getBoat().getBoatId());
        return "reservation-code";
    }


    //////////////////////////////////////////////

    @GetMapping(value = "/boats-parser/filtered")
    public String getListofBoatsForSpecifiedDates(@Valid @ModelAttribute("filterDates") FilterDates filterDates,
                                                  BindingResult result,
                                                  Model model) {
        logger.info("inside boats-parser filtered");
        List<BoatDto> boatList = boatService.findAllAvailableBoats(filterDates.getStartDate(), filterDates.getEndDate());
        model.addAttribute("boats", mapToBoat(boatList));

        return "list-of-boats";

    }

/////////////////////////////////////////////////////////////
// Create account                                          //
/////////////////////////////////////////////////////////////
    @GetMapping("/create-account")
    public String createCustomerAccountForm(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return "create-account-form";
    }

    @PostMapping(value = "create-account")
    public String createCustomerAccount(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                                        BindingResult result,
                                        Model model) {

        if (result.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "create-account-form";
        } else {
            CustomerService.createNewCustomerRecord(customerDto);
            return "account";
        }
    }

/////////////////////////////////////////////////////////////
// Login page                                              //
/////////////////////////////////////////////////////////////

    @GetMapping("/loginPage")
    public String showLoginForm(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login-form";
    }

    @PostMapping(value = "/loginPage")
    public String login(@Valid @ModelAttribute("customer") Login login,
                        BindingResult result,
                        Model model) {

        Customer customer = CustomerService.getCustomerWithGivenLogin(login);
        if (result.hasErrors() || customer == null) {
            model.addAttribute("login", login);
            model.addAttribute("error", "Email or password incorrect");
            return "login-form";
        } else {
            model.addAttribute("customer", customer);
            return "customer-account";
        }
    }

    //---------------------------------------------------------------
    @GetMapping(value = "/loginPage/{id}")
    public String loginToAccount(@PathVariable Long id,
                                 Model model) {

        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        model.addAttribute("customer", customer);
        return "customer-account";
    }

/////////////////////////////////////////////////////////////
// Customer account -> filter boats -> rent a boat         //
/////////////////////////////////////////////////////////////

    @GetMapping("/filter-boats/{id}")
    public String filterBoatsForm(@PathVariable Long id, Model model) {
        FilterDates filterDates = new FilterDates();
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        model.addAttribute("customer", customer);
        model.addAttribute("filterDates", filterDates);
        return "find-boat-filter-form";
    }

    @PostMapping(value = "/filter-boats/{id}")
    public String collectDateFromForm(@PathVariable Long id,
                                      @Valid @ModelAttribute("filterDates") FilterDates filterDates,
                                      BindingResult result,
                                      Model model) {
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        model.addAttribute("customer", customer);


        if (result.hasErrors()) {
            model.addAttribute("filterDates", filterDates);
            return "find-boat-filter-form";
        } else if (filterDates.getStartDate().isAfter(filterDates.getEndDate())) {
            model.addAttribute("filterDates", filterDates);
            return "find-boat-filter-form";
        } else {
            logger.info("inside collectDateFromForm");
            List<BoatDto> boatList = boatService.findAllAvailableBoats(filterDates.getStartDate(), filterDates.getEndDate());
            model.addAttribute("boats", mapToBoat(boatList));
            return "list-of-boats-available";
        }
    }


    @GetMapping(value = "/reservation/id/{id}/boatId/{boatId}/from/{startDate}/to/{endDate}")//id= customerId
    public String createNewReservation(@PathVariable Long id,
                                       @PathVariable Long boatId,
                                       @PathVariable LocalDate startDate,
                                       @PathVariable LocalDate endDate,
                                       Model model) {
        logger.info("inside create reservation");
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));

        Reservation reservation = rentBoatForCustomer(startDate, endDate, customer, Math.toIntExact(boatId));
        model.addAttribute("customer", customer);
        model.addAttribute("reservation", reservation);
        return "boat-rented";
    }

/////////////////////////////////////////////////////////////
// Customer account -> enter reserv code -> rent a boat    //
/////////////////////////////////////////////////////////////
    @GetMapping("/customer-account-reservation/{id}")
    public String enterReservationCodeForm(@PathVariable("id") Long id,
                                           Model model) {
        logger.info("[enterReservationCodeForm] GetMapping ");
        var reservationsWithCode = BoatReservationService.showCodesAndReservations();
        for (String code : reservationsWithCode.keySet()) {
            logger.info("Reservation codes from map: " + code);
        }

        ReservationCode reservationCode = new ReservationCode();
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        model.addAttribute("reservationCode", reservationCode);
        model.addAttribute("customer", customer);
        return "customer-account-reservation-form";

    }

    @PostMapping(value = "/customer-account-reservation/{id}")
    public String enterReservationCode(@PathVariable("id") Long id,
                                       @Valid @ModelAttribute("reservationCode") ReservationCode reservationCode,
                                       BindingResult result,
                                       Model model) {

        logger.info("[enterReservationCode] PostMapping :" + reservationCode.getCode());
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        if (result.hasErrors()) {
            model.addAttribute("reservationCode", reservationCode);
            return "customer-account-reservation-form";
        } else {
            logger.info("[enterReservationCode] " + reservationCode);
            Reservation reservationWithCode = BoatReservationService.rentBoatWithReservationCode(reservationCode.getCode());
            BoatReservationService.rentBoatForCustomer(reservationWithCode.getStartDate(), reservationWithCode.getEndDate(), customer, reservationWithCode.getBoat().getBoatId());

            model.addAttribute("customer", customer);
            model.addAttribute("reservation", reservationWithCode);
            model.addAttribute("boat", reservationWithCode.getBoat());

            return "boat-rented";
        }
    }

    
/////////////////////////////////////////////////////////////
// Customer account -> show rented boats                   //
/////////////////////////////////////////////////////////////
    @GetMapping("/customer-account/{id}/rented-boats")
    public String showRentedBoats(@PathVariable("id") Long id, Model model) {
        logger.info("[showRentedBoats]");
        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        List<Reservation> reservations = BoatReservationService.getListOfReservationsForCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("reservations", reservations);
        return "customer-account-rented-boats";
    }
}


