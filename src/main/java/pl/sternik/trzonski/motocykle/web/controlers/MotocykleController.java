package pl.sternik.trzonski.motocykle.web.controlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;
import pl.sternik.trzonski.motocykle.services.KlaserService;
import pl.sternik.trzonski.motocykle.services.NotificationService;


@Controller
public class MotocykleController {

    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @GetMapping(value = "/motocykle/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Motocykl> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Motocykl motocykl = result.get();
            model.addAttribute("moneta", motocykl);
            return "motocykl";
        } else {
            notifyService.addErrorMessage("Cannot find motocykl #" + id);
            model.clear();
            return "redirect:/motocykle";
        }
    }

    @RequestMapping(value = "/motocykle/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Motocykl> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Motocykl> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Motocykl motocykl = result.get();
            return new ResponseEntity<Motocykl>(motocykl, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return new ResponseEntity<Motocykl>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/motocykle", params = { "save" }, method = RequestMethod.POST)
    public String saveMoneta(Motocykl motocykl, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",notifyService.getNotificationMessages());
            return "moneta";
        }
        Optional<Motocykl> result = klaserService.edit(motocykl);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapisano");
        else
            notifyService.addErrorMessage("Nie udało się zapisać");
        model.clear();
        return "redirect:/motocykle";
    }

    @RequestMapping(value = "/motocykle", params = { "create" }, method = RequestMethod.POST)
    public String createMoneta(Motocykl motocykl, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",notifyService.getNotificationMessages());
            return "moneta";
        }
        klaserService.create(motocykl);
        model.clear();
        notifyService.addInfoMessage("Motocykl został pomyślnie dodany");
        
        return "redirect:/motocykle";
    }

    @RequestMapping(value = "/motocykle", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Motocykl motocykl, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/motocykle";
    }

    @RequestMapping(value = "/motocykle/create", method = RequestMethod.GET)
    public String showMainPages(final Motocykl motocykl) {
        motocykl.setDataProdukcji(Calendar.getInstance().getTime());
        return "motocykl";
    }
}