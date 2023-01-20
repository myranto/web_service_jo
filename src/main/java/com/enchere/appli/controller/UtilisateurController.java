package com.enchere.appli.controller;

import com.enchere.appli.dao.UtilisateurDAO;
import com.enchere.appli.exception.AuthentificationFailedException;
import com.enchere.appli.exception.ResourceNotFoundException;
import com.enchere.appli.model.ObjectReturn;
import com.enchere.appli.model.Utilisateur;
import com.enchere.appli.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("utilisateurs")
public class UtilisateurController
{
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs()
    {
        return utilisateurRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id)
    {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("this user doesn' t exist with id = " +id));
        return ResponseEntity.ok(utilisateur);
    }


    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        return utilisateurRepository.save(utilisateur);
    }

    /*@DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable int id)
    {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The user that you deleted doesn' t exist with id = "+id));
        utilisateurRepository.delete(utilisateur);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @PostMapping({"login"})
    public ObjectReturn login(@RequestBody Utilisateur utilisateur) throws Exception {
        ObjectReturn ans = null;
        Object[] data = null;
        try
        {
            data = UtilisateurDAO.toLog(utilisateur);
            ans = new ObjectReturn();
            ans.setData(data);
            ans.setMessage("Authentification success");
        }
        catch (AuthentificationFailedException e) {
            // TODO Auto-generated catch block
            throw e;
        }
        return ans;
    }

}
