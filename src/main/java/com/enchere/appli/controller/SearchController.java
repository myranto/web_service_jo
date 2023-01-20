package com.enchere.appli.controller;

import com.enchere.appli.model.ObjectReturn;
import com.enchere.appli.model.Produit;
import com.enchere.appli.service.TokenService;
import com.enchere.appli.utils.Search;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
@RequestMapping("searchs")
public class SearchController
{
    @GetMapping
    public ObjectReturn find(@RequestBody Search search, @RequestParam(name="hash",required = true)String hash) throws Exception {
        TokenService.bearerToken(hash);
        ObjectReturn objet = null;
        try
        {
            ArrayList<Produit> p = search.getResult();
            for(Produit pp :p)
            {
                System.out.println(pp.toString());
            }
            objet = new ObjectReturn();
            objet.setData(p);
            objet.setMessage("success");
        }
        catch (Exception e)
        {
            throw e;
        }
        return objet;
    }
}
