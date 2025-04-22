package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class SousCategorieService {

    @Autowired
    private SousCategorieRepository sousCategorieRepository;

    @Autowired
    private EcritureRepository ecritureRepository;

    @Autowired
    private ExerciceRepository exerciceRepository;

    public double getSommeSousCategoriePourDate(SousCategorie sousCategorie, LocalDate date) {
        Exercice exercice = (exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date)).get();
        if (exercice == null) {
            return 0; 
        }

        double somme = 0;
        List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice, date);

        for (Ecriture ecriture : ecritures) {
            if (ecriture.getSousCategorie().equals(sousCategorie)) {
                somme += ecriture.getMontant();
            }
        }

        for (Poste poste : sousCategorie.getPostes()) {
            somme += getSommePostePourDate(poste, date);
        }

        return somme;
    }

    private double getSommePostePourDate(Poste poste, LocalDate date) {
        double somme = 0;

        Exercice exercice = (exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date)).get();
        if (exercice != null) {
            List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice, date);
            for (Ecriture ecriture : ecritures) {
                if (ecriture.getPoste().equals(poste)) {
                    somme += ecriture.getMontant();
                }
            }

            for (Compte compte : poste.getComptes()) {
                somme += getSommeComptePourDate(compte, date);
            }
        }

        return somme;
    }

    private double getSommeComptePourDate(Compte compte, LocalDate date) {
        double somme = 0;

        Exercice exercice = (exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date)).get();
        if (exercice != null) {
            List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice, date);
            for (Ecriture ecriture : ecritures) {
                if (ecriture.getCompte().equals(compte)) {
                    somme += ecriture.getMontant();
                }
            }
        }

        return somme;
    }
}
