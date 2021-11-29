package com.example.bigdata.bigdata;

import com.example.bigdata.bigdata.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitInterface extends JpaRepository<Produit, Integer> {

}
