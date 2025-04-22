package itu.p16.gestion.finance.dto;

public class RubriqueEnfantDTO {
    private Integer id;
    private String nom;

    // Constructeur
    public RubriqueEnfantDTO(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

