package com.enchere.appli.dao;

import com.enchere.appli.accesBase.Connexion;
import com.enchere.appli.model.Compte;
import com.enchere.appli.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteDAO {
    public static void updateCompte(int idCompte, double valeur) throws Exception {
        PreparedStatement stmt = null;
        Connection c = null;

        String sql = "update Compte set valeuractuel = ? where id =? ";
        try {
            c = Connexion.connect();
            stmt = c.prepareStatement(sql);
            stmt.setDouble(1, valeur);
            stmt.setInt(2, idCompte);
            System.out.println(stmt.toString());
            stmt.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            c.close();
        }
    }

    public static Compte findCompteByIdUtilisateur(int id, Connection c) throws Exception {
        Compte cpt = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            String sql = "select * from Compte where Compte.idUtilisateur =  " + id;
            c = Connexion.connect();
            stmt = c.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(res.getInt("idUtilisateur"));
                cpt = new Compte();
                cpt.setId(res.getInt("id"));
                cpt.setUtilisateur(utilisateur);
                cpt.setIsBlocked(res.getBoolean("idblocked"));
                cpt.setValeur_actuel(res.getDouble("valeurActuel"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        System.out.println(cpt.toString());
        return cpt;
    }

    public static Compte findCompteById( int id) throws Exception {
        Compte cpt = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        Connection c = null;

        try {
            String sql = "select * from Compte join Utilisateur on utilisateur.id = compte.idUtilisateur where compte.id =  " + id;
            c = Connexion.connect();
            stmt = c.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur = UtilisateurDAO.findById(res.getInt("idUtilisateur"));
                cpt = new Compte();
                cpt.setId(res.getInt("id"));
                cpt.setUtilisateur(utilisateur);
                cpt.setIsBlocked(res.getBoolean("isblocked"));
                cpt.setValeur_actuel(res.getDouble("valeurActuel"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return cpt;
    }

}
