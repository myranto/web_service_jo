package com.enchere.appli.controller;

import com.enchere.appli.model.Categorie;
import com.enchere.appli.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("categoriesutilisateurs")
public class CategorieControllerUtilisateur
{
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Categorie> getAllCategorie()
    {
        return categorieRepository.findAll();
    }
}
