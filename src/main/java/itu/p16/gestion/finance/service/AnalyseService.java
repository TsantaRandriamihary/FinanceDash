package itu.p16.gestion.finance.service;

import itu.p16.gestion.finance.model.Analyse;
import itu.p16.gestion.finance.model.Categorie;
import itu.p16.gestion.finance.model.Compte;
import itu.p16.gestion.finance.model.Poste;
import itu.p16.gestion.finance.model.SousCategorie;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;


@Service
public class AnalyseService {

    private final SolutionService solutionService;

    public AnalyseService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    public Map<String, Double> calculerAnalyses(List<Categorie> categories) {
        Map<String, Double> analyses = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00"); // Format à deux décimales
    
        double resultatNetExercice = 0;
        double totalProduit = 0;
        double totalActifs = 0; 
        double capitauxPropres = 0; 
        double totalActifsCourants = 0; 
        double totalPassifsCourants = 0; 
        double totalPassifsNonCourants = 0; 
        double stock = 0; 
        double chargesInterets = 0; 
        double resultatOperationnel = 0; 
    
        for (Categorie categorie : categories) {
            if(categorie.getNom().equalsIgnoreCase("bilan actif")) {
                totalActifs = categorie.getSomme();
            }
            for (SousCategorie sousCategorie : categorie.getSousCategories()) {
                switch (sousCategorie.getNom().toLowerCase()) {
                    case "resultat net de l exercice":
                        resultatNetExercice = sousCategorie.getSomme();
                        break;
                    case "total des produits des activites ordinaires":
                        totalProduit = sousCategorie.getSomme();
                        break;
                    case "capitaux propres":
                        capitauxPropres = sousCategorie.getSomme();
                        break;
                    case "actifs courants":
                        totalActifsCourants = sousCategorie.getSomme();
                        stock = sousCategorie.getPostes().stream()
                                    .filter(poste -> poste.getNom().equalsIgnoreCase("Stocks et en-cours"))
                                    .mapToDouble(Poste::getSomme)
                                    .sum();
                        break;
                    case "passifs courants":
                        totalPassifsCourants = sousCategorie.getSomme();
                        break;
                    case "passifs non courants":
                        totalPassifsNonCourants = sousCategorie.getSomme();
                        break;
                    case "resultat financier":
                        chargesInterets = sousCategorie.getPostes().stream()
                        .filter(poste -> poste.getNom().equalsIgnoreCase("Charges financieres"))
                        .flatMap(poste -> poste.getComptes().stream())
                        .filter(compte -> compte.getNumeroCompte().equals("661")) 
                        .mapToDouble(Compte::getSomme)
                        .sum();
                        break;
                    case "resultat operationnel":
                        resultatOperationnel = sousCategorie.getSomme();
                        break;
                    default:
                        break;
                }
            }
        }
    
        // 1. Marge nette
    if (totalProduit != 0) {
        double margeNette = (resultatNetExercice / totalProduit) * 100;
        analyses.put("Marge nette", Double.valueOf(df.format(margeNette).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 2. ROA (Return on Assets)
    if (totalActifs != 0) {
        double roa = (resultatNetExercice / totalActifs) * 100;
        analyses.put("ROA", Double.valueOf(df.format(roa).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 3. ROE (Return on Equity)
    if (capitauxPropres != 0) {
        double roe = (resultatNetExercice / capitauxPropres) * 100;
        analyses.put("ROE", Double.valueOf(df.format(roe).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 4. Ratio de liquidité générale
    if (totalPassifsCourants != 0) {
        double liquiditeGenerale = totalActifsCourants / totalPassifsCourants;
        analyses.put("Ratio de liquidité générale", Double.valueOf(df.format(liquiditeGenerale).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 5. Ratio de liquidité réduite
    if (totalPassifsCourants != 0) {
        double liquiditeReduite = (totalActifsCourants - stock) / totalPassifsCourants;
        analyses.put("Ratio de liquidité réduite", Double.valueOf(df.format(liquiditeReduite).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 6. Ratio d’endettement global
    if (totalActifs != 0) {
        double endettementGlobal = ((totalPassifsCourants + totalPassifsNonCourants) / totalActifs) * 100;
        analyses.put("Ratio d’endettement global", Double.valueOf(df.format(endettementGlobal).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 7. Ratio de couverture des intérêts
    if (chargesInterets != 0) {
        double couvertureInterets = resultatOperationnel / chargesInterets;
        analyses.put("Ratio de couverture des intérêts", Double.valueOf(df.format(couvertureInterets).replace(',', '.'))); // Remplacer la virgule par un point
    }

    // 8. Levier financier
    if (capitauxPropres != 0) {
        double levierFinancier = totalActifs / capitauxPropres;
        analyses.put("Levier financier", Double.valueOf(df.format(levierFinancier).replace(',', '.'))); // Remplacer la virgule par un point
    }
        return analyses;
    }

    public List<Analyse> getAnalyses(Map<String, Double> analyses) {
        List<Analyse> result = new ArrayList<>();
    
        // 1. Marge nette
        if (analyses.containsKey("Marge nette")) {
            double margeNette = analyses.get("Marge nette");
            String interpretationMargeNette = getInterpretationMargeNette(margeNette);
            result.add(new Analyse("Marge nette", margeNette, interpretationMargeNette, null));
        }
    
        // 2. ROA (Return on Assets)
        if (analyses.containsKey("ROA")) {
            double roa = analyses.get("ROA");
            String interpretationROA = getInterpretationROA(roa);
            result.add(new Analyse("ROA", roa, interpretationROA, null));
        }
    
        // 3. ROE (Return on Equity)
        if (analyses.containsKey("ROE")) {
            double roe = analyses.get("ROE");
            String interpretationROE = getInterpretationROE(roe);
            result.add(new Analyse("ROE", roe, interpretationROE, null));
        }
    
        // 4. Ratio de liquidité générale
        if (analyses.containsKey("Ratio de liquidité générale")) {
            double liquiditeGenerale = analyses.get("Ratio de liquidité générale");
            String interpretationLiquiditeGenerale = getInterpretationLiquiditeGenerale(liquiditeGenerale);
            result.add(new Analyse("Ratio de liquidité générale", liquiditeGenerale, interpretationLiquiditeGenerale, null));
        }
    
        // 5. Ratio de liquidité réduite
        if (analyses.containsKey("Ratio de liquidité réduite")) {
            double liquiditeReduite = analyses.get("Ratio de liquidité réduite");
            String interpretationLiquiditeReduite = getInterpretationLiquiditeReduite(liquiditeReduite);
            result.add(new Analyse("Ratio de liquidité réduite", liquiditeReduite, interpretationLiquiditeReduite, null));
        }
    
        // 6. Ratio d’endettement global
        if (analyses.containsKey("Ratio d’endettement global")) {
            double endettementGlobal = analyses.get("Ratio d’endettement global");
            String interpretationEndettementGlobal = getInterpretationEndettementGlobal(endettementGlobal);
            result.add(new Analyse("Ratio d’endettement global", endettementGlobal, interpretationEndettementGlobal, null));
        }
    
        // 7. Ratio de couverture des intérêts
        if (analyses.containsKey("Ratio de couverture des intérêts")) {
            double couvertureInterets = analyses.get("Ratio de couverture des intérêts");
            String interpretationCouvertureInterets = getInterpretationCouvertureInterets(couvertureInterets);
            result.add(new Analyse("Ratio de couverture des intérêts", couvertureInterets, interpretationCouvertureInterets, null));
        }
    
        // 8. Levier financier
        if (analyses.containsKey("Levier financier")) {
            double levierFinancier = analyses.get("Levier financier");
            String interpretationLevierFinancier = getInterpretationLevierFinancier(levierFinancier);
            result.add(new Analyse("Levier financier", levierFinancier, interpretationLevierFinancier, null));
        }
    
        // Générer les solutions en utilisant SolutionService
        solutionService.generateSolutionsInBulk(result);
    
        return result;
    }
    
    private static String getInterpretationMargeNette(double margeNette) {
        if (margeNette < 5) return "La rentabilité de GTM est faible, ce qui peut indiquer que les coûts sont trop élevés ou que les revenus ne sont pas assez importants.";
        if (margeNette <= 10) return "La rentabilité de GTM est correcte, mais il est possible d'améliorer l'efficacité pour obtenir de meilleurs résultats.";
        return "GTM montre une très bonne rentabilité, ce qui signifie que l'entreprise contrôle bien ses coûts et génère des profits.";
    }
    
    private static String getInterpretationROA(double roa) {
        if (roa < 5) return "GTM n'utilise pas assez bien ses actifs pour générer des bénéfices, ce qui peut être amélioré avec une gestion plus efficace des ressources.";
        if (roa <= 10) return "GTM utilise bien ses actifs, mais il y a encore de la place pour les rendre encore plus rentables.";
        return "GTM utilise très bien ses actifs pour générer des profits, ce qui montre une gestion optimale des ressources.";
    }
    
    private static String getInterpretationROE(double roe) {
        if (roe < 10) return "Le rendement pour les actionnaires de GTM est faible, ce qui signifie que l'entreprise ne maximise pas encore son potentiel.";
        if (roe <= 20) return "Le rendement des actionnaires est acceptable, ce qui montre que GTM génère des bénéfices raisonnables pour ses investisseurs.";
        return "GTM offre un excellent rendement à ses actionnaires, maximisant ainsi les bénéfices pour ses investisseurs.";
    }
    
    private static String getInterpretationLiquiditeGenerale(double liquiditeGenerale) {
        if (liquiditeGenerale < 1) return "GTM a un problème de liquidité, ce qui signifie qu'elle pourrait avoir des difficultés à payer ses dettes à court terme.";
        if (liquiditeGenerale <= 2) return "GTM a une bonne gestion des liquidités, assurant une couverture suffisante de ses dettes à court terme.";
        return "GTM a une excellente liquidité, ce qui lui permet de couvrir largement ses dettes à court terme sans souci.";
    }
    
    private static String getInterpretationLiquiditeReduite(double liquiditeReduite) {
        if (liquiditeReduite < 1) return "GTM risque de ne pas pouvoir rembourser ses dettes immédiates sans vendre des stocks, ce qui pourrait être un problème.";
        if (liquiditeReduite <= 1.5) return "GTM a une sécurité minimale pour rembourser ses dettes immédiates, mais cela reste acceptable.";
        return "GTM dispose d'une très bonne capacité pour rembourser ses dettes immédiates sans problème.";
    }
    
    private static String getInterpretationEndettementGlobal(double endettementGlobal) {
        if (endettementGlobal > 70) return "GTM est fortement endettée, ce qui augmente le risque financier et pourrait mettre l'entreprise en difficulté à long terme.";
        if (endettementGlobal >= 50) return "GTM a un niveau d'endettement raisonnable, ce qui lui permet de gérer ses finances sans trop de risques.";
        return "GTM a peu de dettes, ce qui signifie qu'elle est financièrement stable et peu exposée aux risques de solvabilité.";
    }
    
    private static String getInterpretationCouvertureInterets(double couvertureInterets) {
        if (couvertureInterets < 1.5) return "GTM a des difficultés à couvrir ses intérêts, ce qui peut mettre l'entreprise dans une situation financière précaire.";
        if (couvertureInterets <= 3) return "GTM a une couverture des intérêts suffisante, mais une amélioration pourrait être nécessaire pour réduire les risques financiers.";
        return "GTM a une excellente capacité à couvrir ses intérêts, ce qui indique une très bonne santé financière et une faible dépendance à la dette.";
    }

    private static String getInterpretationLevierFinancier(double levierFinancier) {
        if (levierFinancier <= 1.2) 
            return "Faible risque : Les actifs sont principalement financés par des fonds propres, mais la capacité de croissance est limitée.";
        if (levierFinancier <= 2.5) 
            return "Risque modéré : La dette finance une part importante des actifs, ce qui peut être bénéfique si le rendement dépasse le coût de la dette.";
        if (levierFinancier <= 5) 
            return "Risque élevé : Une forte dépendance à la dette augmente les rendements, mais aussi les risques.";
        return "Risque très élevé : L'entreprise est fortement exposée à des difficultés en cas de baisse des revenus ou de hausse des taux d'intérêt.";
    }

        // Méthode pour afficher la liste des analyses finales
    

    
}
