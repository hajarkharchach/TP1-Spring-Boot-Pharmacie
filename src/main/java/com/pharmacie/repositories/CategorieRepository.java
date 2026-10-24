package com.pharmacie.repositories;

import com.pharmacie.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}