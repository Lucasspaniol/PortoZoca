/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests Connection classes
 *
 * @author joaovperin
 */
public class ConexaoFactoryTest {

    /**
     * Tests getConnection methods
     *
     * @throws DBException
     * @throws SQLException
     */
    @Test
    public void getConnection() throws DBException, SQLException {
        ResultSet rs = ConexaoFactory.getConn().createStmt().
                executeQuery("SELECT '1' FROM DUAL;");
        String res = null;
        if (rs.next()) {
            res = rs.getString(1);
            assertEquals("1", res);
        } else {
            throw new AssertionError("Expected database to return 1 but returned " + res);
        }
    }

}
