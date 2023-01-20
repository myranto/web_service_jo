package com.enchere.appli.dao;

import com.enchere.appli.accesBase.Connexion;
import com.enchere.appli.exception.AuthentificationFailedException;
import com.enchere.appli.model.Admin;
import com.enchere.appli.model.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {




    public static Admin getById(int id) throws Exception {
        Connection co = null;
        Admin liste = null;

        try {
            co = Connexion.connect();
            liste = getById(co, id);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            if (co != null)
                co.close();
        }
        return liste;
    }

    public static Admin getById(Connection co, int id) throws SQLException {
        PreparedStatement prst = null;
        ResultSet result = null;
        Admin admin = null;

        try {
            String sql = "Select * from Admin WHERE id = ?";
            prst = co.prepareStatement(sql);
            prst.setInt(1, id);

            result = prst.executeQuery();
            while (result.next()) {
                admin = new Admin();
                admin.setUserName(result.getString("username"));
                admin.setPassWord(result.getString("password"));
                admin.setId(result.getInt("id"));

            }
        } catch (SQLException e) {
            // TODO: handle exception
        } finally {
            if (result != null)
                result.close();
            if (prst != null)
                prst.close();
            if (co != null)
                co.close();
        }
        return admin;
    }


    public static Object[] toLog(Admin x) throws Exception {
        Connection co = null;
        PreparedStatement prst = null;
        ResultSet result = null;
        Admin admin = null;
        Object[] objet = new Object[2];

        try {
            co = Connexion.connect();
            String sql = "Select * from Admin WHERE username = ? AND password = ?";
            prst = co.prepareStatement(sql);
            prst.setString(1, x.getUserName());
            prst.setString(2, x.getPassWord());
            result = prst.executeQuery();
            prst.toString();
            while (result.next()) {
                admin = new Admin();
                admin.setUserName(result.getString("username"));
                admin.setPassWord(result.getString("password"));
                admin.setId(result.getInt("id"));

            }
            if(admin == null)
            {
                throw new AuthentificationFailedException();
            }
            Token token =  TokenDAO.insertToken(admin);
            objet[0] = admin;
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
