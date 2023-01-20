package com.enchere.appli.model;

import com.enchere.appli.dao.CompteDAO;
import com.enchere.appli.dao.UtilisateurDAO;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "registration_date")
    private Date registration_date;

    public Utilisateur(String nom, String prenom, String email, String password, Date registration_date) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.registration_date = registration_date;
    }

    public Utilisateur findById() throws Exception {
        return UtilisateurDAO.findById(this.getId());
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nom='" + getNom() + "'" +
                ", prenom='" + getPrenom() + "'" +
                ", email='" + getEmail() + "'" +
                ", pawsWord='" + getPassword() + "'" +
                ", registration_date='" + getRegistration_date() + "'" +
                "}";
    }

    public Compte getCompte(Connection c) throws Exception {

        Compte cr = CompteDAO.findCompteByIdUtilisateur(this.getId(), c);
        System.out.println("miditra" + cr.toString());
        return cr;
    }
}
