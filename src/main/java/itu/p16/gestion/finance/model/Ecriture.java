package itu.p16.gestion.finance.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Ecriture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_exercice", referencedColumnName = "id", nullable = false)
    private Exercice exercice;

    @ManyToOne
    @JoinColumn(name = "id_categorie", referencedColumnName = "id", nullable = true)
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "id_sous_categorie", referencedColumnName = "id", nullable = true)
    private SousCategorie sousCategorie;

    @ManyToOne
    @JoinColumn(name = "id_poste", referencedColumnName = "id", nullable = true)
    private Poste poste;

    @ManyToOne
    @JoinColumn(name = "id_compte", referencedColumnName = "id", nullable = true)
    private Compte compte;

    private Double montant;

    private String description;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
