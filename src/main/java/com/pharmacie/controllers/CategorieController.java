package com.pharmacie.controllers;

import com.pharmacie.entities.Categorie;
import com.pharmacie.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getById(@PathVariable Long id) {
        return categorieRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categorie create(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Hna zedna l'PUT
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> update(@PathVariable Long id, @RequestBody Categorie categorieDetails) {
        return categorieRepository.findById(id)
                .map(categorie -> {
                    categorie.setNom(categorieDetails.getNom());
                    categorie.setDescription(categorieDetails.getDescription());
                    return ResponseEntity.ok(categorieRepository.save(categorie));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}