/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A factory for database connections
 *
 * @author joaovperin
 */
public class ProdutoDAO extends GenericDAO<Produto> {

    /** Select Statement */
    private static final String SQL_SELECT = "SELECT ProdutoId, Referencia, Descricao, Observacao FROM Produto";

    /**
     * Constructor
     *
     * @param conn
     */
    public ProdutoDAO(Conexao conn) {
        super(conn, Produto.class);
    }

    /**
     * Select method
     *
     * @return List
     * @throws DBException
     */
    @Override
    public final List<Produto> select() throws DBException {
        List<Produto> list = new ArrayList<>();
        try (ResultSet rs = conn.createStmt().executeQuery(SQL_SELECT)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setProdutoId(rs.getInt(1));
                p.setReferencia(rs.getString(2));
                p.setDescricao(rs.getString(3));
                p.setObservacao(rs.getString(4));
                list.add(p);
            }
        } catch (DBException | SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

}
