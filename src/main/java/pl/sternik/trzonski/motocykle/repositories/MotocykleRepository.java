package pl.sternik.trzonski.motocykle.repositories;

import java.util.List;

import pl.sternik.trzonski.motocykle.entities.Motocykl;


public interface MotocykleRepository {
    Motocykl create(Motocykl motocykl) throws MotocyklAlreadyExistsException;
    Motocykl readById(Long id) throws NoSuchMotocyklException;
    Motocykl update(Motocykl motocykl) throws NoSuchMotocyklException;
    void deleteById(Long id) throws NoSuchMotocyklException;
    List<Motocykl> findAll();
}