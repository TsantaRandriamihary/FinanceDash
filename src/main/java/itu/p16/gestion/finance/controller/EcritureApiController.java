package itu.p16.gestion.finance.controller;


import itu.p16.gestion.finance.dto.RubriqueEnfantDTO;
import itu.p16.gestion.finance.model.*;
import itu.p16.gestion.finance.repository.CategorieRepository;
import itu.p16.gestion.finance.repository.PosteRepository;
import itu.p16.gestion.finance.repository.SousCategorieRepository;
import itu.p16.gestion.finance.repository.CompteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EcritureApiController {

    @Autowired
    private SousCategorieRepository sousCategorieRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private CompteRepository compteRepository;

    @GetMapping("/getEnfants")
    public List<RubriqueEnfantDTO> getEnfants(@RequestParam("type") String type, @RequestParam("id") Integer id) {
        switch (type.toLowerCase()) {
            case "categorie":
                List<SousCategorie> sousCategories = sousCategorieRepository.findByCategorieId(id);
                return sousCategories.stream()
                        .map((SousCategorie sousCategorie) -> new RubriqueEnfantDTO(sousCategorie.getId(), sousCategorie.getNom()))
                        .collect(Collectors.toList());

            case "souscategorie":
                List<Poste> postes = posteRepository.findBySousCategorieId(id);
                return postes.stream()
                        .map((Poste poste) -> new RubriqueEnfantDTO(poste.getId(), poste.getNom()))
                        .collect(Collectors.toList());

            case "poste":
                List<Compte> comptes = compteRepository.findByPosteId(id);
                return comptes.stream()
                        .map((Compte compte) -> new RubriqueEnfantDTO(compte.getId(), compte.getNumeroCompte()+"-"+compte.getNom()))
                        .collect(Collectors.toList());

            default:
                throw new RuntimeException("Type de rubrique inconnu : " + type);
        }
    }
}
