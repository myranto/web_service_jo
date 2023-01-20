package com.enchere.appli.dao;

import com.enchere.appli.accesBase.Connexion;
import com.enchere.appli.exception.AuthentificationFailedException;
import com.enchere.appli.model.Admin;
import com.enchere.appli.model.Token;
import com.enchere.appli.model.Utilisateur;

import java.sql.*;

public class UtilisateurDAO {

    public static void newUtilisateur(Utilisateur utilisateur) throws Exception {
        Connection c = Connexion.connect();
        Statement stmt = c.createStatement();
        try {
            String sql = String.format(
                    "INSERT INTO Utilisateur(nom,prenom,email,password,registration_date) values ('%s','%s','%s','%s','%s')",
                    utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getPassword(),
                    utilisateur.getRegistration_date());
            stmt.executeQuery(sql);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            stmt.close();
            c.close();
        }
    }

    public static Utilisateur findById(int id) throws Exception {
        Utilisateur utilisateur = null;
        Statement stmt = null;
        ResultSet res = null;
        Connection c = null;

        try {
            String sql = "select * from utilisateur where id =  " + id;
            c = Connexion.connect();
            stmt = c.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                utilisateur = new Utilisateur(res.getInt("id"), res.getString("nom"), res.getString("prenom"),
                        res.getString("email"), res.getString("password"), res.getDate("registration_date"));
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
        return utilisateur;
    }

    public static Object[] toLog(Utilisateur x) throws Exception {
        Connection co = null;
        PreparedStatement prst = null;
        ResultSet result = null;
        Utilisateur utilisateur = null;
        Object[] objet = new Object[2];

        try {
            co = Connexion.connect();
            String sql = "Select * from utilisateur WHERE email = ? AND password = ?";
            prst = co.prepareStatement(sql);
            prst.setString(1, x.getEmail());
            prst.setString(2, x.getPassword());
            result = prst.executeQuery();
            prst.toString();
            while (result.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setEmail(result.getString("email"));
                utilisateur.setPassword(result.getString("password"));
                utilisateur.setId(result.getInt("id"));

            }
            if(utilisateur == null)
            {
                throw new AuthentificationFailedException();
            }
            Token token =  TokenDAO.insertTokenUtilisateur(utilisateur);
            objet[0] = utilisateur;
            objet[1] = token;
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            if (result != null)
                result.close();
            if (prst != null)
                prst.close();
            if (co != null)
                co.close();
        }
        return objet;
    }
}
