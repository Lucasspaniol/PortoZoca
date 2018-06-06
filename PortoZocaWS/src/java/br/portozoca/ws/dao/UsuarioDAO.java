/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Produto;
import br.portozoca.ws.entidade.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A factory for database connections
 *
 * @author joaovperin
 */
public class UsuarioDAO extends GenericDAO<Usuario> implements DataAccessObject<Usuario> {

    /** Select Statement */
    private static final String SQL_SELECT = "SELECT Apelido, Senha FROM Usuario";

    /**
     * Constructor
     *
     * @param conn
     */
    public UsuarioDAO(Conexao conn) {
        super(conn, Produto.class);
    }

    @Override
    public List<Usuario> select(String filter) throws DBException {
        return doSelect(SQL_SELECT.concat(" ").concat(filter));
    }

    @Override
    public Usuario selectOne(String filter) throws DBException {
        List<Usuario> l = select(filter.concat(" LIMIT 1"));
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    private List<Usuario> doSelect(String selectCmd) throws DBException {
        List<Usuario> list = new ArrayList<>();
        try (Statement stm = conn.createStmt()) {
            try (ResultSet rs = stm.executeQuery(selectCmd)) {
                while (rs.next()) {
                    Usuario l = new Usuario();
                    l.setApelido(rs.getString(1));
                    l.setSenha(rs.getString(2));
                    list.add(l);
                }
            } catch (SQLException e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

}
