/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Produto;
import java.sql.PreparedStatement;
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
public class ProdutoDAO extends GenericDAO<Produto> implements DataAccessObject<Produto> {

    /** Select Statement */
    private static final String SQL_SELECT = "SELECT ProdutoId, Referencia, Descricao, Observacao FROM Produto";
    private static final String SQL_INSERT = "INSERT INTO Produto (ProdutoId, Referencia, Descricao, Observacao) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Produto SET ProdutoId=?, Referencia=?, Descricao=?, Observacao=? WHERE ProdutoId=?";
    private static final String SQL_DELETE = "DELETE FROM Produto WHERE ProdutoId=?";

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
        try (Statement stm = conn.createStmt()) {
            try (ResultSet rs = stm.executeQuery(SQL_SELECT)) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setProdutoId(rs.getInt(1));
                    p.setReferencia(rs.getString(2));
                    p.setDescricao(rs.getString(3));
                    p.setObservacao(rs.getString(4));
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

    /**
     * INSERT a register on the database and returns true if it works
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean insert(Produto bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_INSERT)) {
            int i = 1;
            pstm.setInt(i++, bean.getProdutoId());
            pstm.setString(i++, bean.getReferencia());
            pstm.setString(i++, bean.getDescricao());
            pstm.setString(i++, bean.getObservacao());
            int modifiedRows = pstm.executeUpdate();
            // Sets the generated auto incremented key on the bean
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    bean.setProdutoId(rs.getInt(1));
                }
            }
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * UPDATE a register on the database and returns true if it's modified
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean update(Produto bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_UPDATE)) {
            int i = 1;
            pstm.setInt(i++, bean.getProdutoId());
            pstm.setString(i++, bean.getReferencia());
            pstm.setString(i++, bean.getDescricao());
            pstm.setString(i++, bean.getObservacao());
            // Primary Key
            pstm.setInt(i++, bean.getProdutoId());
            int modifiedRows = pstm.executeUpdate();
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * DELETE a register on the database and returns true if it's deleted
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean delete(Produto bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_DELETE)) {
            int i = 1;
            // Primary Key
            pstm.setInt(i++, bean.getProdutoId());
            int modifiedRows = pstm.executeUpdate();
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
