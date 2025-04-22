package itu.p16.gestion.finance.repository;

import itu.p16.gestion.finance.model.Ecriture;
import itu.p16.gestion.finance.model.Exercice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EcritureRepository extends JpaRepository<Ecriture, Integer> {
    List<Ecriture> findByExerciceAndDateLessThanEqual(Exercice exercice, LocalDate date);
}
