/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Localizacao;
import br.portozoca.ws.entidade.Lpn;
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
 * @author Jonas
 */
public class LpnDAO extends GenericDAO<Lpn> implements DataAccessObject<Lpn> {

    /** Select Statement */
    private static final String SQL_SELECT = "SELECT LpnId, ProdutoId, Quantidade, LpnContenedorId, LocalizacaoId FROM Lpn";
    private static final String SQL_INSERT
            = "INSERT INTO Produto (LpnId, ProdutoId, Quantidade, LpnContenedorId, LocalizacaoId) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE
            = "UPDATE Produto SET LpnId=?, ProdutoId=?, Quantidade=?, LpnContenedorId=?, LocalizacaoId=? WHERE LpnId=?";
    private static final String SQL_DELETE = "DELETE FROM Lpn WHERE LpnId=?";

    /**
     * Constructor
     *
     * @param conn
     */
    public LpnDAO(Conexao conn) {
        super(conn, Lpn.class);
    }

    /**
     * Select method
     *
     * @return List
     * @throws DBException
     */
    @Override
    public final List<Lpn> select() throws DBException {
        DataAccessObject<Produto> produtoDAO = DAOFactory.create(Produto.class, conn);
        DataAccessObject<Localizacao> localizacaoDAO = DAOFactory.create(Localizacao.class, conn);
        List<Lpn> list = new ArrayList<>();
        try (Statement stm = conn.createStmt()) {
            try (ResultSet rs = stm.executeQuery(SQL_SELECT)) {
                while (rs.next()) {
                    Lpn lpn = new Lpn();
                    lpn.setLpnId(rs.getInt(1));
                    lpn.setProduto(produtoDAO.selectOne(" WHERE ProdutoId ='" + rs.getInt(2) + "'"));
                    lpn.setQuantidade(rs.getFloat(3));
                    if(rs.getInt(4) != 0){
                        lpn.setLpnContenedor(this.selectOne(" WHERE LpnId ='" + rs.getInt(4) + "'"));
                    }
                    lpn.setLocalizacao(localizacaoDAO.selectOne(" WHERE LocalizacaoId ='" + rs.getInt(5) + "'"));
                    list.add(lpn);
                }
            } catch (SQLException e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

    @Override
    public Lpn selectOne(String filter) throws DBException {
        DataAccessObject<Produto> produtoDAO = DAOFactory.create(Produto.class, conn);
        DataAccessObject<Localizacao> localizacaoDAO = DAOFactory.create(Localizacao.class, conn);
        try (Statement stm = conn.createStmt()) {
            try (ResultSet rs = stm.executeQuery(SQL_SELECT.concat(filter))) {
                if (rs.next()) {
                    Lpn lpn = new Lpn();
                    lpn.setLpnId(rs.getInt(1));
                    lpn.setProduto(produtoDAO.selectOne(" WHERE ProdutoId ='" + rs.getInt(2) + "'"));
                    lpn.setQuantidade(rs.getFloat(3));
                    if(rs.getInt(4) != 0){
                        lpn.setLpnContenedor(this.selectOne(" WHERE LpnId ='" + rs.getInt(4) + "'"));
                    }
                    lpn.setLocalizacao(localizacaoDAO.selectOne(" WHERE LocalizacaoId ='" + rs.getInt(5) + "'"));
                    return lpn;
                }
            } catch (SQLException e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return null;
    }

    /**
     * INSERT a register on the database and returns true if it works
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean insert(Lpn bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_INSERT)) {
            int i = 1;
            pstm.setInt(i++, bean.getLpnId());
            pstm.setInt(i++, bean.getProduto().getProdutoId());
            pstm.setFloat(i++, bean.getQuantidade());
            pstm.setInt(i++, bean.getLpnContenedor().getLpnId());
            pstm.setInt(i++, bean.getLocalizacao().getLocalizacaoid());
            int modifiedRows = pstm.executeUpdate();
            // Sets the generated auto incremented key on the bean
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    bean.setLpnId(rs.getInt(1));
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
    public boolean update(Lpn bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_UPDATE)) {
            int i = 1;
            pstm.setInt(i++, bean.getLpnId());
            pstm.setInt(i++, bean.getProduto().getProdutoId());
            pstm.setFloat(i++, bean.getQuantidade());
            pstm.setInt(i++, bean.getLpnContenedor().getLpnId());
            pstm.setInt(i++, bean.getLocalizacao().getLocalizacaoid());
            // Primary Key
            pstm.setInt(i++, bean.getLpnId());
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
    public boolean delete(Lpn bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_DELETE)) {
            int i = 1;
            // Primary Key
            pstm.setInt(i++, bean.getLpnId());
            int modifiedRows = pstm.executeUpdate();
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
