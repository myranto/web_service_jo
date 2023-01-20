package com.enchere.appli.utils;


import com.enchere.appli.accesBase.Connexion;
import com.enchere.appli.dao.UtilisateurDAO;
import com.enchere.appli.model.Categorie;
import com.enchere.appli.model.Produit;
import com.enchere.appli.model.Utilisateur;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Search {

    private String def;
    private int categorie;
    private Date date ;


    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String buildDef(){
        if((this.getDef() == null)||(this.getDef().length() == 0)||(this.getDef().compareTo(" ")==0))
            return null;
        return "description LIKE '%"+this.getDef()+"%'";
    }

    private String buildCategorie(){
        if(this.getCategorie()==0)
            return null;
        else
            return " idCategorie ="+this.getCategorie();
    }

    private String buildDate(){
        if(this.getDate() == null)
            return null;
        return "date_ajout ='"+this.getDate()+"'";
    }
    public String buildQuery() throws Exception{
        String query = "Select * from v_recherche  ";
        String [] condition = {this.buildDef(),this.buildDate(),this.buildCategorie()};
        //query = query+this.buildDef();
        System.out.println(condition.length);
        boolean check = false;
        boolean temp =false;
        for(int i = 0 ; i<condition.length;i++){
            System.out.println(condition[i]+" et ");
            if(condition[i] != null){
                if(temp == true)
                    query = query+" AND "+condition[i];
                if((condition[i] != null) &&(check == false)){
                    query = query+" WHERE ";
                    check = true;
                }
                if((check == true)&&(temp == false)){
                    query = query+condition[i];
                    temp = true;
                }
            }
        }
        System.out.println(query);
        return query;

    }


    public  ArrayList<Produit> getResult() throws Exception{
        Connection c = null;
        PreparedStatement stat = null;
        ResultSet res  = null;
        ArrayList<Produit> result = null;
        try{
            c= Connexion.connect();
            stat = c.prepareStatement(this.buildQuery());
            res = stat.executeQuery();
            result  = new ArrayList<Produit>();
            while(res.next()){
                Produit p = new Produit();
                Utilisateur user =UtilisateurDAO.findById(res.getInt("idutilisateur"));
                Categorie ct = new Categorie();
                ct.setId(res.getInt("idcategorie"));
                ct.setNom_categorie(res.getString("nom_categorie"));
                ct.setDescription(res.getString("description"));
                //ct.setDuree(res.getTimestamp("d"));
                p.setId(res.getInt("id"));
                p.setIdutilisateur(user);
                p.setIdcategorie(ct);
                p.setNom_produit(res.getString("nom_produit"));
                p.setDate_ajout(res.getDate("date_ajout"));
                p.setVendu(res.getBoolean("vendu"));
                result.add(p);

            }

        }
        catch(Exception e){
            throw e;
        }
        finally{
            if (res != null) {
                res.close();
            }
            if (stat != null) {
                stat.close();
            }
            if(c!=null)
                c.close();

        }
        return result;
    }
}
