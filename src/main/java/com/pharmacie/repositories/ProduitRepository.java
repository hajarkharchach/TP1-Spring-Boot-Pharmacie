package com.pharmacie.repositories;

import com.pharmacie.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProduitRepository extends JpaRepository<Produit, Long> {
}