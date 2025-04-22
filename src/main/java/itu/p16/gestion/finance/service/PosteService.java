package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PosteService {

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private EcritureRepository ecritureRepository;

    @Autowired
    private ExerciceRepository exerciceRepository;

    public double getSommePostePourDate(Poste poste, LocalDate date) {
        // Trouver l'exercice correspondant à la date donnée
        Exercice exercice = (exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date)).get();
        if (exercice == null) {
            return 0; 
        }

        double somme = 0;
        List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice, date);

        for (Ecriture ecriture : ecritures) {
            if (ecriture.getPoste().equals(poste)) {
                somme += ecriture.getMontant();
            }
        }

        for (Compte compte : poste.getComptes()) {
            somme += getSommeComptePourDate(compte, date);
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
