package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private EcritureRepository ecritureRepository;

    @Autowired
    private ExerciceRepository exerciceRepository;

    public double getSommeCategoriePourDate(Categorie categorie, LocalDate date) {
        Optional<Exercice> exercice = exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date);
        if (!exercice.isPresent()) {
            return 0;
        }

        double somme = 0;
        List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice.get(), date);

        for (Ecriture ecriture : ecritures) {
            if (ecriture.getCategorie() != null && ecriture.getCategorie().equals(categorie)) {
                somme += ecriture.getMontant();
            }
        }

        for (SousCategorie sousCategorie : categorie.getSousCategories()) {
            double sommeSousCategorie = getSommeSousCategoriePourDate(sousCategorie, date);
            somme += sommeSousCategorie;
            sousCategorie.setSomme(sommeSousCategorie);
        }

        categorie.setSomme(somme);

        return somme;
    }

    private double getSommeSousCategoriePourDate(SousCategorie sousCategorie, LocalDate date) {
        double somme = 0;

        Optional<Exercice> exercice = exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date);
        if (exercice.isPresent()) {
            List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice.get(), date);

            for (Ecriture ecriture : ecritures) {
                if (ecriture.getSousCategorie() != null && ecriture.getSousCategorie().equals(sousCategorie)) {
                    somme += ecriture.getMontant();
                }
            }

            for (Poste poste : sousCategorie.getPostes()) {
                double sommePoste = getSommePostePourDate(poste, date);
                somme += sommePoste;
                poste.setSomme(sommePoste);
            }
        }

        sousCategorie.setSomme(somme);

        return somme;
    }

    private double getSommePostePourDate(Poste poste, LocalDate date) {
        double somme = 0;   
        

        Optional<Exercice> exercice = exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date);
        if (exercice.isPresent()) {
            List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice.get(), date);

            for (Ecriture ecriture : ecritures) {
                if (ecriture.getPoste() != null && ecriture.getPoste().equals(poste)) {
                    somme += ecriture.getMontant();
                }
            }

            for (Compte compte : poste.getComptes()) {
                double sommeCompte = getSommeComptePourDate(compte, date);
                somme += sommeCompte;
                compte.setSomme(sommeCompte);
            }
        }

        poste.setSomme(somme);

        return somme;
    }

    private double getSommeComptePourDate(Compte compte, LocalDate date) {
        double somme = 0;

        Optional<Exercice> exercice = exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date);
        if (exercice.isPresent()) {
            List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice.get(), date);

            for (Ecriture ecriture : ecritures) {
                if (ecriture.getCompte() != null && ecriture.getCompte().equals(compte)) {
                    somme += ecriture.getMontant();
                }
            }
        }
        compte.setSomme(somme);
        return somme;
    }
}
