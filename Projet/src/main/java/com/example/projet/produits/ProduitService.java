package com.example.projet.produits;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/produit")
public class ProduitService {
    private final ProduitRepository _ps;

    public ProduitService(ProduitRepository produitRepository) {
        this._ps = produitRepository;
    }

    @GetMapping
    public Collection<Produit> get() {
        return _ps.findAll();
    }

    @GetMapping("/{description}")
    public Produit getByDescription(@PathVariable String description ) {
        return _ps.findByDescription(description);
    }

    @GetMapping("/count")
    public long countProduits() {
        return _ps.count();
    }

    @PostMapping("/{description}/{prix}")
    public Produit add(@PathVariable String description, @PathVariable double prix) {
        if(description.isEmpty()) return null; // on renvoit une erreur si pas de description
        return _ps.insert(new Produit(null, description, prix)); // sinon on insert le produit
    }

    @DeleteMapping
    public void remove(@RequestBody Produit p) {
        _ps.delete(p);
    }
}
