package com.pharmacie.controllers;

import com.pharmacie.entities.Produit;
import com.pharmacie.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller 
@RequestMapping("/produits")
public class ProduitViewController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public String listProduits(Model model) {
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits", produits);
        return "produits"; // Renvoie le fichier templates/produits.html
    }
}