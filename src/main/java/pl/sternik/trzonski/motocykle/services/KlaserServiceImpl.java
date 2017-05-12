package pl.sternik.trzonski.motocykle.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.repositories.MotocyklAlreadyExistsException;
import pl.sternik.trzonski.motocykle.repositories.MotocykleRepository;
import pl.sternik.trzonski.motocykle.repositories.NoSuchMotocyklException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private MotocykleRepository bazaDanych;

    @Override
    public List<Motocykl> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Motocykl> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Motocykl> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchMotocyklException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Motocykl> create(Motocykl motocykl) {
        try {
            return Optional.of(bazaDanych.create(motocykl));
        } catch (MotocyklAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Motocykl> edit(Motocykl motocykl) {
        try {
            return Optional.of(bazaDanych.update(motocykl));
        } catch (NoSuchMotocyklException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMotocyklException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Motocykl> findLatest3() {
        return Collections.emptyList();
    }

	@Override
	public List<Motocykl> findAllDublety() { 
		return bazaDanych.findAll();
	}

}
