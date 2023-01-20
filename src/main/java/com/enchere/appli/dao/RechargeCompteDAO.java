package com.enchere.appli.dao;

import com.enchere.appli.accesBase.Connexion;
import com.enchere.appli.model.Compte;
import com.enchere.appli.model.RechargeCompte;
import com.enchere.appli.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RechargeCompteDAO {

    public static ArrayList<RechargeCompte> listeDemandeRecharge() throws Exception {
        Connection co = null;
        PreparedStatement prst = null;
        ResultSet result = null;
        ArrayList<RechargeCompte> list = new ArrayList<RechargeCompte>();
        try {
            co = Connexion.connect();
            String sql = "Select * from rechargecompte join compte on compte.id = rechargecompte.idcompte join utilisateur on utilisateur.id=Compte.idutilisateur where rechargecompte.isvalid='false'";

            prst = co.prepareStatement(sql);
            result = prst.executeQuery();
            while (result.next()) {
                RechargeCompte temp = new RechargeCompte();
                temp.setId(result.getInt("id"));
                Utilisateur util = new Utilisateur();
                util.setId(result.getInt("idUtilisateur"));
                util.setNom(result.getString("nom"));
                util.setPrenom(result.getString("prenom"));
                util.setEmail(result.getString("email"));
                util.setPassword(result.getString("password"));
                util.setRegistration_date(result.getDate("registration_date"));

                Compte cpt = new Compte();
                cpt.setId(result.getInt("idCompte"));
                cpt.setUtilisateur(util);

                temp.setCompte(cpt);
                temp.setValeur_recharge(result.getDouble("valeur_recharge"));
                temp.setDaterechargement(result.getDate("daterechargement"));
                temp.setIsValid(result.getBoolean("isValid"));
                list.add(temp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            if (result != null)
                co.close();
            if (prst != null)
                prst.close();
            if (co != null)
                co.close();
        }
        return list;
    }

    public static RechargeCompte findById(int id) throws SQLException {
        RechargeCompte rcpt = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        Connection c = null;

        try {
            String sql = "select * from rechargecompte where id = " + id;
            c = Connexion.connect();
            stmt = c.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                Compte cpt = new Compte();
                cpt.setId(res.getInt("idcompte"));
                cpt = CompteDAO.findCompteById(res.getInt("idcompte"));

                rcpt = new RechargeCompte();
                rcpt.setId(res.getInt("id"));
                rcpt.setCompte(cpt);
                rcpt.setValeur_recharge(res.getDouble("valeur_recharge"));
                rcpt.setDaterechargement(res.getDate("daterechargement"));
                rcpt.setIsValid(res.getBoolean("isvalid"));

            }
        } catch (SQLException e) {
            // TODO: handle exception
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return rcpt;
    }

    public static void updateRechargeCompte(int id) throws Exception {
        PreparedStatement stmt = null;
        Connection c = null;

        String sql = "update rechargecompte set isvalid = 'true' where id = ? ";
        try {
            c = Connexion.connect();
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            System.out.println(stmt.toString());
            stmt.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            c.close();
        }
    }

}
