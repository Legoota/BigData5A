package com.example.projet.produits;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProduitRepository extends MongoRepository<Produit, String> {
    Produit findByDescription(String desc);
}
