package com.enchere.appli.repository;

import com.enchere.appli.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>
{
    // All CRUD database methods
}
