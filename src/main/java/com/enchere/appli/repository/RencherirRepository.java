package com.enchere.appli.repository;

import com.enchere.appli.model.Rencherir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RencherirRepository extends JpaRepository<Rencherir, Integer>
{
    //All CRUD method
    @Query(value = "select * from rencherir where idProduit =:idproduit order by mise desc",nativeQuery = true)
    List<Rencherir> getRencherirById(int idproduit);
}
