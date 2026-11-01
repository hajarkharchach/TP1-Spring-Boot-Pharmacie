package com.pharmacie.controllers;
import com.pharmacie.entities.DetailProduit;
import com.pharmacie.repositories.DetailProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/DetailProduits")
public class DetailProduitController {
	
	@Autowired
	private DetailProduitRepository detailProduitRepository ;
	
	@GetMapping
	public List<DetailProduit> getAll(){
		return detailProduitRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailProduit> getById(@PathVariable Long id){
		return detailProduitRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public DetailProduit create (@RequestBody DetailProduit detailProduit ) {
		return detailProduitRepository.save(detailProduit);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DetailProduit> update (@PathVariable Long id , @RequestBody DetailProduit detail ){
		return detailProduitRepository.findById(id).map(detailProduit ->{
			detailProduit.setComposition(detail.getComposition());
			detailProduit.setDosage(detail.getDosage());
			detailProduit.setFabricant(detail.getFabricant());
			detailProduit.setIndications(detail.getIndications());
			detailProduit.setPaysOrigine(detail.getPaysOrigine());
			return ResponseEntity.ok(detailProduitRepository.save(detailProduit));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		if(detailProduitRepository.existsById(id)) {
			detailProduitRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.notFound().build();
	}
}
