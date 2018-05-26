/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author joaovperin
 */
public interface Conexao extends AutoCloseable {

    public boolean isFree();

    public void free();

    public Statement createStmt() throws DBException;

    public PreparedStatement prepareStmt(String sql) throws DBException;

    public void commit() throws DBException;

    public void rollback() throws DBException;

    @Override
    public void close() throws DBException;

}
