package itu.p16.gestion.finance.model;

public class Analyse {

    private String nomAnalyse;
    private double valeur;
    private String interpretation;
    private String solution;

    // Constructeur
    public Analyse(String nomAnalyse, double valeur, String interpretation, String solution) {
        this.nomAnalyse = nomAnalyse;
        this.valeur = valeur;
        this.interpretation = interpretation;
        this.solution = solution;
    }

    // Getters et Setters
    public String getNomAnalyse() {
        return nomAnalyse;
    }

    public void setNomAnalyse(String nomAnalyse) {
        this.nomAnalyse = nomAnalyse;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    // Méthode pour afficher les détails d'une analyse
    @Override
    public String toString() {
        return "Analyse: " + nomAnalyse + "\nValeur: " + valeur + "\nInterprétation: " + interpretation + "\nSolution: " + solution;
    }
}
