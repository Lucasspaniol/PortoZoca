/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Localizacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Spaniol
 */
public class LocalizacaoDAO extends GenericDAO<Localizacao> implements DataAccessObject<Localizacao> {

    /** Select Statement */
    private static final String SQL_SELECT = "SELECT LocalizacaoId, LocalizacaoSuperiorId, Divisao, Observacao FROM Localizacao";
    private static final String SQL_INSERT = "INSERT INTO Localizacao (LocalizacaoId, LocalizacaoSuperiorId, Divisao, Observacao) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Localizacao SET LocalizacaoId=?, LocalizacaoSuperiorId=?, Divisao=?, Observacao=? WHERE ProdutoId=?";
    private static final String SQL_DELETE = "DELETE FROM Localizacao WHERE LocalizacaoId=?";

    /**
     * Constructor
     *
     * @param conn
     */
    public LocalizacaoDAO(Conexao conn) {
        super(conn, Localizacao.class);
    }

    /**
     * Select
     *
     * @return Lista
     * @throws DBException
     */
    @Override
    public final List<Localizacao> select() throws DBException {
        List<Localizacao> list = new ArrayList<>();
        try (Statement stm = conn.createStmt()) {
            try (ResultSet rs = stm.executeQuery(SQL_SELECT)) {
                while (rs.next()) {
                    Localizacao l = new Localizacao();
                    l.setLocalizacaoid(rs.getInt(1));
                    
                    l.setDivision(rs.getString(3));
                    l.setObservacao(rs.getString(4));
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

    /**
     * INSERT
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean insert(Localizacao bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_INSERT)) {
            int i = 1;
            pstm.setInt(i++, bean.getLocalizacaoid());
            pstm.setInt(i++, bean.getSup().getLocalizacaoid());
            pstm.setString(i++, bean.getDivision());
            pstm.setString(i++, bean.getObservacao());
            int modifiedRows = pstm.executeUpdate();
            // Sets the generated auto incremented key on the bean
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    bean.setLocalizacaoid(rs.getInt(1));
                }
            }
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * UPDATE
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean update(Localizacao bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_UPDATE)) {
            int i = 1;
            pstm.setInt(i++, bean.getLocalizacaoid());
            pstm.setInt(i++, bean.getSup().getLocalizacaoid());
            pstm.setString(i++, bean.getDivision());
            pstm.setString(i++, bean.getObservacao());
            // Primary Key
            pstm.setInt(i++, bean.getLocalizacaoid());
            int modifiedRows = pstm.executeUpdate();
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    /**
     * DELETE
     *
     * @param bean
     * @return List
     * @throws DBException
     */
    @Override
    public boolean delete(Localizacao bean) throws DBException {
        try (PreparedStatement pstm = conn.prepareStmt(SQL_DELETE)) {
            int i = 1;
            // Primary Key
            pstm.setInt(i++, bean.getLocalizacaoid());
            int modifiedRows = pstm.executeUpdate();
            return (modifiedRows >= 1);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
