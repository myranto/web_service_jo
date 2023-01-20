package com.enchere.appli.controller;


import com.enchere.appli.model.Rencherir;
import com.enchere.appli.repository.RencherirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("rencherirs")
public class RencherirController
{
    @Autowired
    private RencherirRepository rencherirRepository;

    @GetMapping
    public List<Rencherir> getList()
    {
        return rencherirRepository.findAll();
    }

    @GetMapping("{idproduit}")
    public List<Rencherir> getRencherirById(@PathVariable int idproduit) throws  Exception
    {
        return  rencherirRepository.getRencherirById(idproduit);
    }

}
