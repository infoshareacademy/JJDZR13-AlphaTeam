package pl.isa.alphateam.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoatController {
    private static final Logger logger = LoggerFactory.getLogger(BoatController.class);


    @GetMapping("/menu")
    public String index(Model model){
logger.info("inside index");

        return "menu";
    }

}
