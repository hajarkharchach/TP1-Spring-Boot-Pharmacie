package com.pharmacie.controllers;

import com.pharmacie.entities.Fournisseur;
import com.pharmacie.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping
    public List<Fournisseur> getAll() {
        return fournisseurRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getById(@PathVariable Long id) {
        return fournisseurRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fournisseur create(@RequestBody Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> update(@PathVariable Long id, @RequestBody Fournisseur fournisseurDetails) {
        return fournisseurRepository.findById(id)
                .map(fournisseur -> {
                    fournisseur.setNom(fournisseurDetails.getNom());
                    fournisseur.setContact(fournisseurDetails.getContact());
                    fournisseur.setAdresse(fournisseurDetails.getAdresse());
                    return ResponseEntity.ok(fournisseurRepository.save(fournisseur));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if ( fournisseurRepository.existsById(id)) {
            fournisseurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}