package itu.p16.gestion.finance.dto;

import itu.p16.gestion.finance.model.Categorie;

public class CategorieEtat {
    private Categorie categorie;
    private double somme;

    public CategorieEtat(Categorie categorie, double somme) {
        this.categorie = categorie;
        this.somme = somme;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }
}
