package com.enchere.appli.controller;


import com.enchere.appli.dao.ProduitDAO;
import com.enchere.appli.exception.ResourceNotFoundException;
import com.enchere.appli.model.Produit;
import com.enchere.appli.repository.ProduitRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("produits")
public class ProduitController
{
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public List<Produit> getListProduit()
    {
        return produitRepository.getListProduit();
    }

    @GetMapping("{id}")
    public Produit getProduitsById(@PathVariable int id) throws Exception {
        Produit p = produitRepository.getProduitsById(id);
        if(p == null) throw new Exception("null");
        return p;

    }

    @PostMapping
    public Produit newProduit(@RequestBody Produit produit)
    {
        return produitRepository.save(produit);
    }

    @PutMapping("{id}")
    public void updateProduit(@PathVariable int id) throws Exception
    {
        ProduitDAO.updateProduit(id);
    }
}