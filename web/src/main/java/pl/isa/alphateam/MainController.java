package pl.isa.alphateam;

import pl.isa.alphateam.BoatService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static pl.isa.alphateam.Constants.*;

@Controller
public class MainController {

    @Autowired
    BoatService boatService;

    /**
     * Adnotacja poniżej pozwala na zadeklarowanie atrybutu dla Thymeleaf, który
     * będzie widoczny globalnie, niezależnie która metoda kontrolera obsłuży żądanie.
     * @param request obiekt posiadający informacje o otrzymanym żądaniu http
     * @return zwrócona wartość będzie przypisana do atrybutu "content" i dostępna
     * w szablonach Thymeleaf
     */
    @ModelAttribute("content")
    public String content(final HttpServletRequest request) {
        return request.getRequestURI().equals("/") ? "index" : // jeśli jesteśmy w root, chcemy wartość "index"
                request.getRequestURI().substring(1); // w przeciwnym przypadku obcinamy tylko '/' z początku
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("dateTime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "main";
    }

    @GetMapping("/info")
    String info(Model model) {
        model.addAttribute("login", INFO_LOGIN)
                .addAttribute("surname", INFO_SURNAME)
                .addAttribute("lastname", INFO_LASTNAME)
                .addAttribute("adress", INFO_ADRESS);
        return "main";

    }
    @GetMapping("/boatsavailable")
    String boatsavailable(Model model) {
        model.addAttribute("boat1", boatService.getBoat(1742L))
                .addAttribute("boat2", boatService.getBoat(2149L));
        return "main";
    }
}
