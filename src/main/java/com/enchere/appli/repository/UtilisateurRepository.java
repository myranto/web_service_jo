package com.enchere.appli.repository;

import com.enchere.appli.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>
{
    //all CRUD database methods
}
