package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.CategorieRepository;
import itu.p16.gestion.finance.repository.ExerciceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtatFinancierService {

    @Autowired
    private ExerciceRepository exerciceRepository;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private SousCategorieService sousCategorieService;

    @Autowired
    private PosteService posteService;

    @Autowired
    private CompteService compteService;

    
    public List<String> obtenirAvertissements(List<Categorie> categories) throws Exception {
        
        List<String> avertissements = new ArrayList<>();
        
        // Vérification des indices pour les catégories 1, 2 et 3
        if (categories.size() > 2) {
            double sommeCategorie1 = categories.get(0).getSomme();
            double sommeCategorie2 = categories.get(1).getSomme();
            
            if (sommeCategorie1 != sommeCategorie2) {
                avertissements.add("L'état financier n'est pas équilibré. La somme des actifs (" 
                    + sommeCategorie1 + ") n'est pas égale à la somme des passifs (" + sommeCategorie2 + ").");
            }
    
            // Vérification de la somme de la catégorie à l'indice 3
            if (categories.size() > 3) {
                 double sommeCategorie3 = categories.get(2).getSomme();
    
                // Si la somme de la catégorie 3 est égale à 0, on l'ajoute dans les avertissements
                if (sommeCategorie3 == 0) {
                    avertissements.add("Votre état financier ne présente aucun résultat.");
                }
            }
        }
    
        return avertissements;
    }
    




    public List<Categorie> obtenirEtatFinancier(LocalDate date) throws Exception {
        Optional<Exercice> exerciceOpt = exerciceRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(date, date);
        if (exerciceOpt.isEmpty()) {
            throw new Exception("Aucun exercice trouvé pour la date donnée");
        }

        Exercice exercice = exerciceOpt.get();

        List<Categorie> categories = categorieRepository.findAll();

        for (Categorie categorie : categories) {
            double sommeCategorie = categorieService.getSommeCategoriePourDate(categorie, date);
            categorie.setSomme(sommeCategorie);     
        }
        
        return categories;
    }
}
