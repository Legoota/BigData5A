package com.example.projet.produits;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "produit")
public class Produit implements Serializable {
    @Id
    private String id; // id auto-généré par mongodb
    @Field("description")
    private String description;
    @Field("prix")
    private double prix;

    public Produit() {}

    public Produit(String id, String description, double prix) {
        this.id = id;
        this.description = description;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id: '" + id + '\'' +
                ", description: '" + description + '\'' +
                ", prix: " + prix +
                '}';
    }
}
