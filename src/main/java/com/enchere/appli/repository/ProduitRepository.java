package com.enchere.appli.repository;

import com.enchere.appli.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer>
{
    //All CRUD database methods
    @Query(value = "select * from produit where vendu = false",nativeQuery = true)
    List<Produit> getListProduit();

    @Query(value = "select * from produit where id=:id AND vendu='false'",nativeQuery = true)
    Produit getProduitsById(int id);


}
