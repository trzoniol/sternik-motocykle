package pl.sternik.trzonski.motocykle.web.controlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;
import pl.sternik.trzonski.motocykle.services.KlaserService;
import pl.sternik.trzonski.motocykle.services.NotificationService;


@Controller
public class KlaserController {

    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notificationService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("coinsAll")
    public List<Motocykl> populateCoins() {
        return this.klaserService.findAll();
    }

    @ModelAttribute("coinsToSell")
    public List<Motocykl> populateCoinsToSell() {
        return this.klaserService.findAllToSell();
    }
    
    @ModelAttribute("coinsDublety")
    public List<Motocykl> populateCoinsDublety() {
        return this.klaserService.findAllDublety();
    }

    @ModelAttribute("coinsLast3")
    public List<Motocykl> populateLast3Coins() {
        return this.klaserService.findLatest3();
    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/motocykle", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "tosell";
    }
    
    @RequestMapping("/dublety")
    public String showDubletylPage() {
        return "dublety";
    }

}
