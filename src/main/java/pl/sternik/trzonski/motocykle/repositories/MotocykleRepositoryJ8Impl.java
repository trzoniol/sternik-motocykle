package pl.sternik.trzonski.motocykle.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;


@Service
@Qualifier("lista")
public class MotocykleRepositoryJ8Impl implements MotocykleRepository {

    private List<Motocykl> motocykle = new ArrayList<Motocykl>() {
        {
            add(Motocykl.produceMotocykl(1L, "Honda", "Hornet", new BigDecimal("10000"), new Date("2005/01/01"), Status.NOWA));
            add(Motocykl.produceMotocykl(2L, "Honda", "CBR", new BigDecimal("15000"), new Date("2010/01/01"), Status.DUBLET));
            add(Motocykl.produceMotocykl(3L, "Suzyki", "GS", new BigDecimal("4000"), new Date("1998/03/01"), Status.DO_SPRZEDANIA));
            add(Motocykl.produceMotocykl(4L, "Honda", "Hornet", new BigDecimal("10000"), new Date("2005/01/01"), Status.NOWA));
            add(Motocykl.produceMotocykl(5L, "Honda", "Hornet", new BigDecimal("10000"), new Date("2005/01/01"), Status.NOWA));
        }
    };

    @Override
    public List<Motocykl> findAll() {
        return this.motocykle;
    }

    @Override
    public Motocykl readById(Long id) throws NoSuchMotocyklException {
        return this.motocykle.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchMotocyklException::new);
    }

    @Override
    public Motocykl create(Motocykl motocykl) {
        if (!motocykle.isEmpty()) {
            motocykl.setNumerKatalogowy(
                    this.motocykle.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            motocykl.setNumerKatalogowy(1L);
        }
        this.motocykle.add(motocykl);
        return motocykl;
    }

    @Override
    public Motocykl update(Motocykl motocykl) throws NoSuchMotocyklException {
        for (int i = 0; i < this.motocykle.size(); i++) {
            if (Objects.equals(this.motocykle.get(i).getNumerKatalogowy(), motocykl.getNumerKatalogowy())) {
                this.motocykle.set(i, motocykl);
                return motocykl;
            }
        }
        throw new NoSuchMotocyklException("Nie ma takiego motocykla: " + motocykl.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchMotocyklException {
        for (int i = 0; i < this.motocykle.size(); i++) {
            if (Objects.equals(this.motocykle.get(i).getNumerKatalogowy(), id)) {
                this.motocykle.remove(i);
            }
        }
        throw new NoSuchMotocyklException("Nie ma takiego motocykla: " + id);
    }

}
