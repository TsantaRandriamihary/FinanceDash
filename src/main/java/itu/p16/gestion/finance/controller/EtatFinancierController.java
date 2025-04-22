package itu.p16.gestion.finance.controller;

import itu.p16.gestion.finance.model.Categorie;
import itu.p16.gestion.finance.model.Compte;
import itu.p16.gestion.finance.model.Poste;
import itu.p16.gestion.finance.model.SousCategorie;
import itu.p16.gestion.finance.service.EtatFinancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EtatFinancierController {

    @Autowired
    private EtatFinancierService etatFinancierService;

    @PostMapping("/etatFinancier")
    public String afficherEtatFinancier(@RequestParam("date") String date, Model model) {
        double exercicenet = 0;
        try {
           List<Categorie> categories = etatFinancierService.obtenirEtatFinancier(LocalDate.parse(date));
           for (Categorie categorie : categories) {
            System.out.println("NOM DU CATEGORIE : "+categorie.getNom());
            if (categorie.getNom().equalsIgnoreCase("Compte de Resultat")) {
                double productionExercice = 0;
                double consommationExercice = 0;
                double valeurAjouteeExploitation = 0;
                double excedentBrutExploitation = 0;
                double autresProduitsOperationnels = 0;
                double reprisesSurProvisions = 0;
                double produitsFinanciers = 0;
                double chargesFinancieres = 0;
                double dotationsAmortissements = 0;
                double autresChargesOperationnelles = 0;
                double resultatOperationnel = 0;
                double resultatFinancier = 0;
                double resultatAvantImpots = 0;
                double impotsExigibles = 0; 
                double impotsDifferes = 0; 
                double resultatNetActivitesOrdinaires = 0;
                double extraordinaireProduit = 0;
                double extraordinaireCharge = 0;
                double resultatExtraordinaire = 0; 
                double resultatNetExercice = 0;
                double totalCharge = 0;
                double totalProduit = 0;

                for (SousCategorie sousCategorie : categorie.getSousCategories()) {
                    switch (sousCategorie.getNom().toLowerCase()) {
                        case "production de l exercice":
                            productionExercice = sousCategorie.getSomme();
                            break;
                        case "consommation de l exercice":
                            consommationExercice = sousCategorie.getSomme();
                           
                            break;
                        case "excedent brut d exploitation":
                            excedentBrutExploitation = sousCategorie.getSomme();

                            break;
                        case "resultat operationnel":
                            autresProduitsOperationnels = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getNom().equalsIgnoreCase("Autres produits operationnels"))
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            reprisesSurProvisions = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getNom().equalsIgnoreCase("Reprises sur provisions et pertes de valeur"))
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            dotationsAmortissements = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getNom().equalsIgnoreCase("Dotations aux amortissements, provisions et pertes de valeur"))
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            autresChargesOperationnelles = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getNom().equalsIgnoreCase("Autres charges operationnelles"))
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            
                            break;
                        case "resultat financier":
                            produitsFinanciers = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getNom().equalsIgnoreCase("Produits financiers"))
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            chargesFinancieres = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getNom().equalsIgnoreCase("Charges financieres"))
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            
                            
                            break;
                        case "resultat net des activites ordinaires":
                            impotsExigibles = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getId() == 47)
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            impotsDifferes = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getId() == 48)
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            
                            break;
                        case "resultat extraordinaire":
                            extraordinaireProduit = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getId() == 49)
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            extraordinaireCharge = sousCategorie.getPostes().stream()
                                .filter(poste -> poste.getId() == 50)
                                .mapToDouble(Poste::getSomme)
                                .sum();
                            
                            break;
                        default:
                            break;
                    }
                    
                }
                System.out.println("Production de l'exercice: " + productionExercice);
                System.out.println("Consommation de l'exercice: " + consommationExercice);
                System.out.println("Excédent brut d'exploitation: " + excedentBrutExploitation);
                System.out.println("Autres produits opérationnels: " + autresProduitsOperationnels);
                            System.out.println("Reprises sur provisions: " + reprisesSurProvisions);
                            System.out.println("Dotations aux amortissements: " + dotationsAmortissements);
                            System.out.println("Autres charges opérationnelles: " + autresChargesOperationnelles);
                            System.out.println("Produits financiers: " + produitsFinanciers);
                            System.out.println("Charges financières: " + chargesFinancieres);
                            System.out.println("Impôts exigibles: " + impotsExigibles);
                            System.out.println("Impôts différés: " + impotsDifferes);
                            System.out.println("Éléments extraordinaires (produits): " + extraordinaireProduit);
                            System.out.println("Éléments extraordinaires (charges): " + extraordinaireCharge);
                            
                            
                            

                totalProduit = productionExercice + autresProduitsOperationnels + reprisesSurProvisions + produitsFinanciers;

                totalCharge = consommationExercice + excedentBrutExploitation + dotationsAmortissements + chargesFinancieres;

                valeurAjouteeExploitation = productionExercice - consommationExercice;

                excedentBrutExploitation = valeurAjouteeExploitation - excedentBrutExploitation;

                resultatOperationnel = excedentBrutExploitation + autresProduitsOperationnels + reprisesSurProvisions - autresChargesOperationnelles - dotationsAmortissements;

                resultatFinancier = produitsFinanciers - chargesFinancieres;

                resultatAvantImpots = resultatOperationnel + resultatFinancier;

                resultatNetActivitesOrdinaires = resultatAvantImpots - impotsExigibles - impotsDifferes;

                resultatExtraordinaire = extraordinaireProduit - extraordinaireCharge; 

                resultatNetExercice = resultatNetActivitesOrdinaires + resultatExtraordinaire;
                exercicenet = resultatNetExercice;

                // Affichage des résultats
                System.out.println("Total Produit: " + totalProduit);
                System.out.println("Total Charge: " + totalCharge);
                System.out.println("Valeur Ajoutée à l'Exploitation: " + valeurAjouteeExploitation);
                System.out.println("Excedent Brut d'Exploitation: " + excedentBrutExploitation);
                System.out.println("Résultat Opérationnel: " + resultatOperationnel);
                System.out.println("Résultat Financier: " + resultatFinancier);
                System.out.println("Résultat Avant Impôts: " + resultatAvantImpots);
                System.out.println("Résultat Net des Activités Ordinaires: " + resultatNetActivitesOrdinaires);
                System.out.println("Résultat Extraordinaire: " + resultatExtraordinaire);
                System.out.println("Résultat Net de l'Exercice: " + resultatNetExercice);

                for (SousCategorie sousCategorie : categorie.getSousCategories()) {
                    switch (sousCategorie.getNom().toLowerCase()) {
                        case "total des produits des activites ordinaires":
                            sousCategorie.setSomme(totalProduit);
                            break;
                        case "total des charges des activites ordinaires":
                            sousCategorie.setSomme(totalCharge);
                            break;
                        case "valeur ajoutee d exploitation":
                            sousCategorie.setSomme(valeurAjouteeExploitation);
                            break;
                        case "excedent brut d exploitation":
                            sousCategorie.setSomme(excedentBrutExploitation);
                            break;
                        case "resultat operationnel":
                            sousCategorie.setSomme(resultatOperationnel);
                            break;
                        case "resultat financier":
                            sousCategorie.setSomme(resultatFinancier);
                            break;
                        case "resultat avant impots":
                            sousCategorie.setSomme(resultatAvantImpots);
                            break;
                        case "resultat net des activites ordinaires":
                            sousCategorie.setSomme(resultatNetActivitesOrdinaires);
                            break;
                        case "resultat extraordinaire":
                            sousCategorie.setSomme(resultatExtraordinaire);
                            break;
                        case "resultat net de l exercice":
                            sousCategorie.setSomme(resultatNetExercice);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

            if (categories.size() > 1) {
                Categorie categorie1 = categories.get(1);
                
                if (!categorie1.getSousCategories().isEmpty()) {
                    SousCategorie sousCategorie0 = categorie1.getSousCategories().get(0);
                    
                    Poste posteResultatNetPartGroupe = sousCategorie0.getPostes().stream()
                        .filter(poste -> poste.getId() == 22)
                        .findFirst()
                        .orElse(null); 
                    
                    if (posteResultatNetPartGroupe != null) {
                        posteResultatNetPartGroupe.setSomme(posteResultatNetPartGroupe.getSomme()+exercicenet);
                    }
            sousCategorie0.setSomme(sousCategorie0.getSomme() + exercicenet);
            
            categorie1.setSomme(categorie1.getSomme() + exercicenet);
                }
            }
        
           for (Categorie categorie : categories) {
            System.out.println("Catégorie: " + categorie.getNom() + ", Somme: " + categorie.getSomme());

            for (SousCategorie sousCategorie : categorie.getSousCategories()) {
                System.out.println("  Sous-catégorie: " + sousCategorie.getNom() + ", Somme: " + sousCategorie.getSomme());

                for (Poste poste : sousCategorie.getPostes()) {
                    System.out.println("    Poste: " + poste.getNom() + ", Somme: " + poste.getSomme());

                    for (Compte compte : poste.getComptes()) {
                        System.out.println("      Compte: " + compte.getNom() + ", Somme: " + compte.getSomme());
                    }
                }
            }
        }
        List<String> avertissements = etatFinancierService.obtenirAvertissements(categories);
        System.out.println("Categorie 0: " + categories.get(0).getSomme()+" Categorie 1: "+categories.get(1).getSomme()+" categories 2 :"+categories.get(2).getSomme());
            model.addAttribute("categories", categories);
            model.addAttribute("avertissements", avertissements);
            return "etat";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur: Aucune donnee ou exercice correspondant a la date "+date);
            e.printStackTrace();
            return "etat";
        }
    }


    

    @GetMapping("/etatFinancier")
    public String afficherFormulaire() {
        
        return "etat";
    }

    
}
