/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;

/**
 * A Factory utility to create DAO Objects
 *
 * @author joaovperin
 */
public final class DAOFactory {

    public static final <D> D createDao(Class<D> dao, Conexao conn) {
        try {
            return dao.getConstructor(Conexao.class).newInstance(conn);
        } catch (IllegalArgumentException | SecurityException | ReflectiveOperationException e) {
            throw new RuntimeException("Tu fez merda, programador de bosta.");
        }
    }

    /**
     * Instantiate a new Data Access Object for the bean class
     *
     * @param <B>
     * @param bean
     * @param conn
     * @return DataAccessObject
     */
    public static final <B> DataAccessObject<B> create(Class<B> bean, Conexao conn) {
        try {
            Class<B> daoClass = (Class<B>) Class.forName("br.portozoca.ws.dao.".concat(bean.getSimpleName()).concat("DAO"));
            return (DataAccessObject<B>) daoClass.getConstructor(Conexao.class).newInstance(conn);
        } catch (IllegalArgumentException | SecurityException | ReflectiveOperationException e) {
            return new GenericDAO<>(conn, bean);
        }
    }

}
