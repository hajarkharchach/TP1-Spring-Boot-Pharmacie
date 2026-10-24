package com.pharmacie.controllers;

import com.pharmacie.entities.Produit;
import com.pharmacie.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        return produitRepository.findById(id)
                .map(produit -> {
                    produit.setNom(produitDetails.getNom());
                    produit.setDescription(produitDetails.getDescription());
                    produit.setPrix(produitDetails.getPrix());
                    produit.setCodeBarre(produitDetails.getCodeBarre());
                    produit.setDateExpiration(produitDetails.getDateExpiration());
                    produit.setCategorie(produitDetails.getCategorie());
                    produit.setFournisseur(produitDetails.getFournisseur());
                    produit.setDetailProduit(produitDetails.getDetailProduit());
                    produit.setTags(produitDetails.getTags());
                    return ResponseEntity.ok(produitRepository.save(produit));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}