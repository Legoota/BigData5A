package com.example.projet.produits;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produit")
public class ProduitService {
    private final ProduitRepository _ps;

    public ProduitService(ProduitRepository produitRepository) {
        this._ps = produitRepository;
    }

    @GetMapping("/{description}")
    public Produit getById(@PathVariable String description )
    {
        return _ps.findByDescription(description);
    }

    @GetMapping("/{desingation}/{prix}")
    public Produit add(@PathVariable String description, @PathVariable double prix)
    {
        if(!description.isEmpty())
        {
            return _ps.insert(new Produit(null, description, prix));
        }
        else
        {
            return null;
        }

    }
}
