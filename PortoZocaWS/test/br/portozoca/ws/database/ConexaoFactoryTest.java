/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Connection classes
 *
 * @author joaovperin
 */
public class ConexaoFactoryTest {

    private static Conexao conn;

    @Before
    public void beforeEach() throws DBException {
        conn = ConexaoFactory.getConn();
    }

    @After
    public void afterEach() throws DBException {
        conn.close();
    }

    /**
     * Tests getConnection methods
     *
     * @throws DBException
     * @throws SQLException
     */
    @Test
    public void getConnection() throws DBException, SQLException {
        ResultSet rs = conn.createStmt().executeQuery("SELECT '1' FROM DUAL;");
        String res = null;
        if (rs.next()) {
            res = rs.getString(1);
            assertEquals("1", res);
        } else {
            throw new AssertionError("Expected database to return 1 but returned " + res);
        }
    }

    /**
     * Ensure that database has the tables
     *
     * @throws DBException
     * @throws SQLException
     */
    @Test
    public void showTables() throws Exception {
        ResultSet rs = conn.createStmt().executeQuery("Show Tables;");
        List<String> tables = new ArrayList<>();
        while (rs.next()) {
            tables.add(rs.getString(1));
        }
        // Asserts
        assertTrue(tables.contains("Localizacoes"));
        assertTrue(tables.contains("Lpns"));
        assertTrue(tables.contains("Produtos"));
        assertTrue(tables.contains("Usuarios"));
    }

}
