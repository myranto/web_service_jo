package com.enchere.appli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produit")
public class Produit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idutilisateur")
    private Utilisateur idutilisateur;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idcategorie")
    private Categorie idcategorie;

    @Column(name = "nom_produit")
    private String nom_produit;

    @Column(name = "date_ajout")
    private Date date_ajout;

    @Column(name = "vendu")
    private boolean vendu;

    @Column(name = "duree")
    private Timestamp duree;

}