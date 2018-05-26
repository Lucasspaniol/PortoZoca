/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import br.portozoca.ws.utils.CryptUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaovperin
 */
public class DatabaseTesting implements Runnable {

    public static void main(String[] args) {
        new DatabaseTesting().run();
    }

    @Override
    public void run() {
        System.out.println("Start.");
        try {
            Connection conn = getConn();
            ResultSet rs = conn.createStatement().executeQuery("SELECT '1' FROM DUAL;");
            if (rs.next()) {
                System.out.println("Result: " + rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("End.");
    }

    private String url = null;
    private String user = null;
    private String pass = null;

    private Connection getConn() throws SQLException {
        // If driver is already registred/configured
        if (url != null && user != null && pass != null) {
            return DriverManager.getConnection(url, user, pass);
        }
        // Sets the properties
        url = "jdbc:mysql://porto-zoca.cl6eed1myiqo.us-west-2.rds.amazonaws.com:3306";
        user = "root";
        pass = CryptUtils.base64().decrypt("UG9ydG9ab2Nh");
        // Registers the driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return getConn();
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

}
