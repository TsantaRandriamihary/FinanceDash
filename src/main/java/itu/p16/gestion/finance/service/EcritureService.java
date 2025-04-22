package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EcritureService {

    @Autowired
    private ExerciceRepository exerciceRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private SousCategorieRepository sousCategorieRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private EcritureRepository ecritureRepository;

    public Ecriture insererEcriture(String dateStr, Integer idCategorie, Integer idSousCategorie, 
                                    Integer idPoste, Integer idCompte, Double montant, String description) {

        LocalDate date = LocalDate.parse(dateStr);

        Exercice exercice = exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date)
                                             .orElseThrow(() -> new RuntimeException("Aucun exercice trouvé pour la date donnée"));

        Categorie categorie = null;
        SousCategorie sousCategorie = null;
        Poste poste = null;
        Compte compte = null;
        
        if (idCompte != null) {
            compte = compteRepository.findById(idCompte).orElse(null);
            if (compte != null) {
                categorie = null;
                sousCategorie = null;
                poste = null;
            }
        }
        else if (idPoste != null) {
            poste = posteRepository.findById(idPoste).orElse(null);
            if (poste != null) {
                categorie = null;
                sousCategorie = null;
                compte = null;
            }
        } else if (idSousCategorie != null) {
            sousCategorie = sousCategorieRepository.findById(idSousCategorie).orElse(null);
            if (sousCategorie != null) {
                categorie = null;
                poste = null;
                compte = null;
            }
        } else if (idCategorie != null) {
            categorie = categorieRepository.findById(idCategorie).orElse(null);
            if (categorie != null) {
                sousCategorie = null;
                poste = null;
                compte = null;
            }
        } 

        // Vérification que l'une des entités a bien été trouvée
        if (poste == null && sousCategorie == null && categorie == null && compte == null) {
            throw new RuntimeException("Aucune rubrique valide trouvée pour l'écriture");
        }

        // Création de l'écriture
        Ecriture ecriture = new Ecriture();
        ecriture.setDate(date);
        ecriture.setExercice(exercice);
        ecriture.setCategorie(categorie);
        ecriture.setSousCategorie(sousCategorie);
        ecriture.setPoste(poste);
        ecriture.setCompte(compte);
        ecriture.setMontant(montant);
        ecriture.setDescription(description);

        return ecritureRepository.save(ecriture);
    }
}
