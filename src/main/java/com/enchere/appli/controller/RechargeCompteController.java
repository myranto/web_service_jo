package com.enchere.appli.controller;

import com.enchere.appli.dao.CompteDAO;
import com.enchere.appli.dao.RechargeCompteDAO;
import com.enchere.appli.model.Compte;
import com.enchere.appli.model.ObjectReturn;
import com.enchere.appli.model.RechargeCompte;
import com.enchere.appli.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("recharges")
public class RechargeCompteController
{
    @GetMapping
    public ObjectReturn listeDemandeRecharge(@RequestParam(name = "hash", required = true)String hash) throws Exception
    {
        TokenService.bearerToken(hash);
        ObjectReturn data = new ObjectReturn();
        try
        {
            ArrayList<RechargeCompte> recharge = RechargeCompteDAO.listeDemandeRecharge();
            data.setData(recharge);
            data.setMessage("succes");
        }
        catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            throw e;
        }
        return data;
    }

    @PostMapping
    public ObjectReturn findById(@RequestBody RechargeCompte re,@RequestParam(name="hash", required = true) String hash) throws Exception
    {
        TokenService.bearerToken(hash);
        ObjectReturn objet = null;
        System.out.println(re.getId());
        try
        {
            RechargeCompte rc = RechargeCompteDAO.findById(re.getId());
            rc.valider();

            int idcompte =  rc.getCompte().getId();
            Compte cpt = CompteDAO.findCompteById(idcompte);


            objet = new ObjectReturn();
            objet.setData(cpt);
            objet.setMessage("Succes");
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
        return objet;
    }


}
