package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private EcritureRepository ecritureRepository;

    @Autowired
    private ExerciceRepository exerciceRepository;

    public double getSommeComptePourDate(Compte compte, LocalDate date) {
        Exercice exercice = (exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date)).get();
        if (exercice == null) {
            return 0; 
        }

        double somme = 0;
        List<Ecriture> ecritures = ecritureRepository.findByExerciceAndDateLessThanEqual(exercice, date);

        for (Ecriture ecriture : ecritures) {
            if (ecriture.getCompte().equals(compte)) {
                somme += ecriture.getMontant();
            }
        }

        return somme;
    }
}
