package com.enchere.appli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rencherir")
public class Rencherir
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idproduit")
    private Produit idproduit;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idutilisateur")
    private Utilisateur idutilisateur;

    @Column(name = "datemise")
    private Date datemise;

    @Column(name = "mise")
    private double mise;

}
