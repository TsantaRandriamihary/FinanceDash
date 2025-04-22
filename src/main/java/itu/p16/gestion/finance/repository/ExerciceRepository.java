package itu.p16.gestion.finance.repository;

import itu.p16.gestion.finance.model.Exercice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Integer> {

    Optional<Exercice> findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(LocalDate dateDebut, LocalDate dateFin);
}
