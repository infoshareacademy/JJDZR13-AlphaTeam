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
import pl.isa.alphateam.service.BoatService;
import pl.isa.alphateam.service.BoatServiceImpl;
import pl.isa.alphateam.service.CustomerService;

import java.util.List;

import static pl.isa.alphateam.boat.BoatMapper.mapToBoat;


@Controller
public class BoatController {
    private static final Logger logger = LoggerFactory.getLogger(BoatController.class);

    private BoatService boatService;


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
       // List<BoatDto> boatList = boatService.findAllBoats();
       // model.addAttribute("boats", boatList);
        return "list-of-boats";
    }

    @GetMapping("/boats-parser")
    public String getListOfBoatsParser(Model model) {
        logger.info("inside boats-parser");
        List<Boat> boatList = JSONParserBoat.getListOfBoatsFromDatabase();
        model.addAttribute("boats", boatList);
        return "list-of-boats";
    }

   @GetMapping(value = "/boats-parser/filtered")
    public String getListofBoatsForSpecifiedDates(@Valid @ModelAttribute("filterDates") FilterDates filterDates,
                             BindingResult result,
                             Model model) {

       logger.info("inside boats-parser filtered");
       List<BoatDto> boatList = boatService.findAllAvailableBoats(filterDates.getStartDate(), filterDates.getEndDate());
       model.addAttribute("boats", mapToBoat(boatList));

       return "list-of-boats";

   }


    @GetMapping("/create-account")
    public String createToolForm(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return "create-account-form";
    }

    @PostMapping(value = "create-account")
    public String createTool(@Valid @ModelAttribute("customer") CustomerDto customerDto,
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

    @GetMapping(value = "/loginPage/{id}")
    public String loginToAccount(@PathVariable Long id,
                                 Model model) {

        Customer customer = CustomerService.getCustomerWithGivenId(Math.toIntExact(id));
        model.addAttribute("customer", customer);
        return "customer-account";
    }
}


