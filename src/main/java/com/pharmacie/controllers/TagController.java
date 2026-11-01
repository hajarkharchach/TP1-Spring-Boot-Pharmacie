package com.pharmacie.controllers;

import com.pharmacie.entities.Tag;
import com.pharmacie.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getById( @PathVariable Long id) {
    	return tagRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tag> update (@PathVariable Long id , @RequestBody Tag tagDetails){
    	return tagRepository.findById(id).map(tag -> {
    		tag.setLibelle(tagDetails.getLibelle());
    		return ResponseEntity.ok(tagRepository.save(tag));
    	})
    	.orElse(ResponseEntity.notFound().build());
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	if(tagRepository.existsById(id)) {
    		tagRepository.deleteById(id);
    		return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.notFound().build();
    }
}