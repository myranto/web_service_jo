package com.enchere.appli.accesBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

    public static Connection connect() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection("jdbc:postgresql://chunee.db.elephantsql.com/pkwqkahk", "pkwqkahk",
                "GVbCqVVa10nbuDrWmVmiA2kKfSDwZb4D");
        return connect;
    }
}
