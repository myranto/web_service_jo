package com.enchere.appli.controller;


import com.enchere.appli.model.Categorie;
import com.enchere.appli.repository.CategorieRepository;
import com.enchere.appli.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("categories")
public class CategorieController
{
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Categorie> getAllCategorie(@RequestParam(name = "hash",required = true)String hash) throws Exception {
        TokenService.bearerToken(hash);
        return categorieRepository.findAll();
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie,@RequestParam(name = "hash",required = true)String hash) throws Exception {
        TokenService.bearerToken(hash);
        return categorieRepository.save(categorie);
    }

}
