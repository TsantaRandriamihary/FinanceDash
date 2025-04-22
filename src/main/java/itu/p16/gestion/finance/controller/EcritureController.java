package itu.p16.gestion.finance.controller;

import itu.p16.gestion.finance.model.Categorie;
import itu.p16.gestion.finance.service.EcritureService;
import itu.p16.gestion.finance.repository.CategorieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EcritureController {

    @Autowired
    private EcritureService ecritureService;

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("")
    public String afficherFormulaire(Model model) {
        List<Categorie> categories = categorieRepository.findAll();
        model.addAttribute("categories", categories); 
    for (Categorie categorie : categories) {
        System.out.println(categorie.getNom());
    }
        return "insertion"; 
    }

    @PostMapping("/submitForm")
    public String handleFormSubmission(
            @RequestParam("date") String date,
            @RequestParam(value = "idCategorie", required = false) Integer idCategorie,
            @RequestParam(value = "idSousCategorie", required = false) Integer idSousCategorie,
            @RequestParam(value = "idPoste", required = false) Integer idPoste,
            @RequestParam(value = "idCompte", required = false) Integer idCompte,
            @RequestParam("montant") Double montant,
            @RequestParam("description") String description,
            RedirectAttributes redirectAttributes) {

        try {
            ecritureService.insererEcriture(date, idCategorie, idSousCategorie, idPoste, idCompte, montant, description);

            redirectAttributes.addFlashAttribute("message", "Écriture enregistrée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur : " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:";
    }
}
