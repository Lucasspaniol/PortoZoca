/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Localizacao;
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

    /**
     * Executes a SELECT statement on the database and returns a list of beans
     * ...respecting a filter
     *
     * @param filter
     * @return List
     * @throws DBException
     */
    public List<B> select(String filter) throws DBException;

    /**
     * Executes a SELECT statement on the database and returns one result, given
     * ...a filter
     *
     * @param filter
     * @return List
     * @throws DBException
     */
    public B selectOne(String filter) throws DBException;

    /**
     * INSERT a register on the database and returns true if it works
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    public boolean insert(B bean) throws DBException;

    /**
     * UPDATE a register on the database and returns true if it's modified
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    public boolean update(B bean) throws DBException;

    /**
     * DELETE a register on the database and returns true if it's deleted
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    public boolean delete(B bean) throws DBException;

    public Localizacao loadLocalizacao(String parameter) throws DBException ;

}
