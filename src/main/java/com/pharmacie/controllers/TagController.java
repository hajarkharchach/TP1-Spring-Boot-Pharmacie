package com.pharmacie.controllers;

import com.pharmacie.entities.Tag;
import com.pharmacie.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }
}