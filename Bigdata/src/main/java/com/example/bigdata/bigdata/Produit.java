package com.example.bigdata.bigdata;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Pour la génération des constructeurs, des getters et setters, on aurait pu utiliser Lobook
 */
@Entity
public class Produit {
    @Id
    private int identifiant;
    private String designation;
    private double prix;

    public Produit(){}

    public Produit(int identifiant, String designation, double prix) {
        this.identifiant = identifiant;
        this.designation = designation;
        this.prix = prix;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public String getDesignation() {
        return designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "com.example.bigdata.bigdata.Produit{" +
                "identifiant=" + identifiant +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                '}';
    }
}
