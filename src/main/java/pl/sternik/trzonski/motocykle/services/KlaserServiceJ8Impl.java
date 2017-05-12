package pl.sternik.trzonski.motocykle.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;
import pl.sternik.trzonski.motocykle.repositories.MotocyklAlreadyExistsException;
import pl.sternik.trzonski.motocykle.repositories.MotocykleRepository;
import pl.sternik.trzonski.motocykle.repositories.NoSuchMotocyklException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private MotocykleRepository motocykle;

    @Override
    public List<Motocykl> findAll() {
        return motocykle.findAll();
    }

    @Override
    public List<Motocykl> findLatest3() {
        return motocykle.findAll().stream().sorted((a, b) -> b.getDataProdukcji().compareTo(a.getDataProdukcji())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Motocykl> findById(Long id) {
        try {
            return Optional.of(motocykle.readById(id));
        } catch (NoSuchMotocyklException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Motocykl> create(Motocykl motocykl) {
        try {
            return Optional.of(motocykle.create(motocykl));
        } catch (MotocyklAlreadyExistsException e) {
            try {
                return Optional.of(motocykle.readById(motocykl.getNumerKatalogowy()));
            } catch (NoSuchMotocyklException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Motocykl> edit(Motocykl motocykl) {
        try {
            return Optional.of(motocykle.update(motocykl));
        } catch (NoSuchMotocyklException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
        	motocykle.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMotocyklException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Motocykl> findAllToSell() {
        return motocykle.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }

	@Override
	public List<Motocykl> findAllDublety() {
		return motocykle.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DUBLET))
                .collect(Collectors.toList());
	}
}
