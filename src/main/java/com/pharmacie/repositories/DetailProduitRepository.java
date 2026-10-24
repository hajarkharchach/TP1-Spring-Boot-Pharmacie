package com.pharmacie.repositories;

import com.pharmacie.entities.DetailProduit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {
}