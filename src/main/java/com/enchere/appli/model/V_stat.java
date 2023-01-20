package com.enchere.appli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="v_stat")
public class V_stat
{
    @Column(name = "nom_categorie")
    private ArrayList<String> nom_categorie;

    @Column(name = "prix_moyenne")
    private ArrayList<Double> prix_moyenne;


}
