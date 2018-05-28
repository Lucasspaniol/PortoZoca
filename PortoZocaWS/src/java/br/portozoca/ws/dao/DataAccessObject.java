/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.DBException;
import java.util.List;

/**
 * An interface for DAOs
 *
 * @author joaovperin
 * @param <B> Bean class
 */
public interface DataAccessObject<B> {

    /**
     * Executes a SELECT statement on the database and returns a list of beans
     *
     * @return List
     * @throws DBException
     */
    public List<B> select() throws DBException;

}
