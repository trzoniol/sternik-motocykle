package pl.sternik.trzonski.motocykle.web.controlers;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;
import pl.sternik.trzonski.motocykle.repositories.MotocyklAlreadyExistsException;
import pl.sternik.trzonski.motocykle.repositories.MotocykleRepository;
import pl.sternik.trzonski.motocykle.repositories.NoSuchMotocyklException;



@Controller
public class WprawkiController {

    @Autowired
    @Qualifier("tablica")
    MotocykleRepository baza;
    
    @RequestMapping(path = "/wprawki", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }
    
    @GetMapping("/wprawki3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
    
    @GetMapping(value = "/wprawki/motocykles/{id}/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Motocykl> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Motocykl m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Motocykl>(m, HttpStatus.OK);
            
        } catch (NoSuchMotocyklException e) {
            System.out.println(e.getClass().getName());
            m = new Motocykl();
            m.setNumerKatalogowy(id);
            m.setProducent("Polska");
            m.setModel("1");
            m.setCena(new BigDecimal("12"));
            m.setDataProdukcji(new Date());
            m.setStatus(Status.NOWA);
            try {
                baza.create(m);
            } catch (MotocyklAlreadyExistsException e1) {
                System.out.println(e1.getClass().getName());
            }
            return new ResponseEntity<Motocykl>(m, HttpStatus.CREATED);
        }
    }

}
