/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.ConexaoFactory;
import br.portozoca.ws.database.DBException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Generic Data Access Object
 *
 * @author joaovperin
 * @param <B> Bean class
 */
public class GenericDAO<B> {

    /** Connection to the database */
    protected final Conexao conn;

    /**
     * Constructor that receives the connection
     *
     * @param conn
     */
    public GenericDAO(Conexao conn) {
        this.conn = Objects.requireNonNull(conn, "A valid connection is needed.");
    }

    /**
     * TESTING PURPOSES ONLY.
     *
     * @param args
     * @throws DBException
     */
    public static void main(String[] args) throws DBException {
        try (Conexao c = ConexaoFactory.query()) {
            new GenericDAO<>(c).select().forEach((p) -> {
                System.out.println("Produto: " + p);
            });
        }
    }

    /**
     * Executes a SELECT statement on the database and returns a list of beans
     *
     *
     * @return List<B>
     * @throws DBException
     */
    public List<B> select() throws DBException {
        List<B> list = new ArrayList<>();
        try (ResultSet rs = conn.createStmt().executeQuery(buildSelectStmt())) {
            while (rs.next()) {
                B bean = createBean();
                Class<?> beanClazz = bean.getClass();
                int i = 1;
                for (Field field : beanClazz.getDeclaredFields()) {
                    try {
                        Object value = getGetterFromRs(field).invoke(rs, i++);
                        getSetter(beanClazz, field).invoke(bean, value);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        throw new DBException(e);
                    }
                }
                list.add(bean);
            }
        } catch (DBException | SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

    /**
     * Create a new Bean Instance
     *
     * @return B
     */
    public B createBean() {
        try {
            // TODO: Unhardcode this.
            return (B) Class.forName("br.portozoca.ws.entidade.Produto").newInstance();
//            return (B) Class.forName(this.getClass().getSimpleName().replace("DAO", "")).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Method getSetter(Class c, Field f) {
        try {
//            if (f.getType().equals(Boolean.class)) {
//                return c.getMethod("is" + f.getName(), Boolean.class);
//            }
            return c.getMethod("set" + capitalize(f.getName()), f.getType());
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Method getGetterFromRs(Field f) {
        try {
            return ResultSet.class.getMethod("get" + capitalize(f.getType().getSimpleName()), int.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Capitalizes a field
     *
     * @param input
     * @return String
     */
    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase().concat(input.substring(1, input.length()));
    }

    /**
     * Build the Select Statement
     *
     * @return String
     */
    private String buildSelectStmt() {
        // TODO: Unhardcode this.
        return "SELECT ProdutoId, Referencia, Descricao, Observacao FROM Produto";
    }

}
