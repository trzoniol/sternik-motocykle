package pl.sternik.trzonski.motocykle.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements MotocykleRepository {

    private Motocykl[] baza;

    public ProstaBazaDanych() {
        baza = new Motocykl[15];
        Motocykl m = new Motocykl();
        m.setNumerKatalogowy(0L);
        m.setProducent("Polska");
        m.setModel("1");
        m.setCena(new BigDecimal("12"));
        m.setDataProdukcji(new Date());
        m.setStatus(Status.NOWA);
        baza[0] = m;
        m = new Motocykl();
        m.setProducent("Polska");
        m.setModel("1");
        m.setCena(new BigDecimal("12"));
        m.setDataProdukcji(new Date());
        m.setStatus(Status.NOWA);
        baza[2] = m;

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Motocykl[rozmiarBazy];
    }

    @Override
    public Motocykl create(Motocykl motocykl) throws MotocyklAlreadyExistsException {
        if (motocykl.getNumerKatalogowy() != null && baza[motocykl.getNumerKatalogowy().intValue()] != null) {
            if (motocykl.getNumerKatalogowy().equals(baza[motocykl.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new MotocyklAlreadyExistsException("Już jest moneta o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = motocykl;
                motocykl.setNumerKatalogowy((long) i);
                return motocykl;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchMotocyklException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchMotocyklException("Nie poprawny numer katologowy");
        }
        baza[numerKatalogowy] = null;
    }

    @Override
    public Motocykl update(Motocykl motocykl) throws NoSuchMotocyklException {
        int numerKatalogowy = motocykl.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchMotocyklException("Nie poprawny numer katologowy");
        }

        Motocykl m = baza[motocykl.getNumerKatalogowy().intValue()];
        if (m == null) {
            throw new NoSuchMotocyklException("Brak takiego motocykla.");
        } else {
            baza[motocykl.getNumerKatalogowy().intValue()] = motocykl;
        }
        return motocykl;
    }

    @Override
    public Motocykl readById(Long numerKatalogowy) throws NoSuchMotocyklException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchMotocyklException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Motocykl> findAll() {
        List<Motocykl> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
