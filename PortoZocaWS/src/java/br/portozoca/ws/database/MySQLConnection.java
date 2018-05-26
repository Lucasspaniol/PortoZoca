/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An implementation of a MySQL Connection
 *
 * @author joaovperin
 */
public class MySQLConnection implements ConexaoPool {

    /** JDBC wrapped connection object */
    private final Connection conn;
    /** An indicator if the connection is a transaction or a read-only one */
    private final boolean readOnly;
    /** A flag to control if the connection is free to use */
    private boolean free;

    /**
     * Constructor that receives the connection and default's to readonly
     *
     * @param conn
     */
    public MySQLConnection(Connection conn) {
        this(conn, true);
    }

    /**
     * Constructor that receives the connection and read-only/transaction mode
     *
     * @param conn
     */
    public MySQLConnection(Connection conn, boolean readOnly) {
        this.conn = conn;
        this.readOnly = readOnly;
        this.free = true;
    }

    @Override
    public Statement createStmt() throws DBException {
        try {
            this.free = false;
            return conn.createStatement();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public PreparedStatement prepareStmt(String sql) throws DBException {
        try {
            this.free = false;
            return conn.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public void free() {
        this.free = true;
    }

    @Override
    public boolean isFree() {
        return free;
    }

    @Override
    public void commit() throws DBException {
        if (readOnly) {
            System.out.println("*** Cannot Commit on Read-Only connection.");
            return;
        }
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new DBException("Failed to commit!", e);
        }
    }

    @Override
    public void rollback() {
        if (readOnly) {
            System.out.println("*** Cannot rollback on Read-Only connection.");
            return;
        }
        try {
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("*** Failed to rollback.");
        }
    }

    @Override
    public void close() {
        // If is a ready-only connection, just mark it as free and not close that.
        if (readOnly) {
            this.free = true;
            return;
        }
        jdbcClose();
    }

    @Override
    public final void jdbcClose() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("*** Failed to close.");
        }
    }

}
