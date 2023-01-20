package com.enchere.appli.dao;

import com.enchere.appli.accesBase.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProduitDAO
{
    public static void updateProduit(int id) throws Exception {
        PreparedStatement stmt = null;
        Connection c = null;

        String sql = "update Produit set vendu = 'true' where id =? ";
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
