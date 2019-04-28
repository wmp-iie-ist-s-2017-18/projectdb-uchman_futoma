/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabi
 */
public class DbPolaczenie {

    private static final String BAZA = "kino.sqlite";
    private static final String DRIVER = "org.sqlite.JDBC";

    public Connection Connect() {
        try {
            String url = "jdbc:sqlite:"+BAZA;
            String user = "";
            String password = "";

            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(url); // w razie problemu dopisac url, user, password
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbPolaczenie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
