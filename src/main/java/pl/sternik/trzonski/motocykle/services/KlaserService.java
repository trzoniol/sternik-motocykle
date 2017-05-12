package pl.sternik.trzonski.motocykle.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.trzonski.motocykle.entities.Motocykl;


public interface KlaserService {
    List<Motocykl> findAll();

    List<Motocykl> findAllToSell();

    Optional<Motocykl> findById(Long id);

    Optional<Motocykl> create(Motocykl motocykl);

    Optional<Motocykl> edit(Motocykl motocykl);

    Optional<Boolean> deleteById(Long id);

    List<Motocykl> findLatest3();

	List<Motocykl> findAllDublety();
}