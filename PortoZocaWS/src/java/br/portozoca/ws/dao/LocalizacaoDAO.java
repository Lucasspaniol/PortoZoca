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
    private static final String SQL_UPDATE = "UPDATE Localizacao SET LocalizacaoId=?, LocalizacaoSuperiorId=?, Divisao=?, Observacao=? WHERE LocalizacaoId=?";
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
        return doSelect(SQL_SELECT, null);
    }

    @Override
    public List<Localizacao> select(String filter) throws DBException {
        return doSelect(SQL_SELECT.concat(" ").concat(filter), null);
    }

    @Override
    public Localizacao selectOne(String filter) throws DBException {
        List<Localizacao> l = select(filter.concat(" LIMIT 1"));
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    private List<Localizacao> doSelect(String selectCmd, String alias) throws DBException {
        List<Localizacao> list = new ArrayList<>();
        try (Statement stm = conn.createStmt()) {
            try (ResultSet rs = stm.executeQuery(selectCmd)) {
                while (rs.next()) {
                    Localizacao l = new Localizacao();
                    l.setLocalizacaoid(rs.getInt(1));
                    // Se possuir localização superior, lẽ ela e seta no objeto
                    int localizacaoSuperior = rs.getInt(2);
                    if (localizacaoSuperior != 0) {
                        String myAlias = "";
                        if(alias != null){
                            myAlias = "."+alias;
                        }
                        Localizacao superior = selectOne(String.format("WHERE %sLocalizacaoId = '%d'", myAlias, localizacaoSuperior));
                        l.setSup(superior);
                    }
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
            Localizacao sup = bean.getSup();
            // If it have a superior location
            if (sup != null) {
                pstm.setInt(i++, sup.getLocalizacaoid());
            } else {
                pstm.setNull(i++, java.sql.Types.INTEGER);
            }
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
            Localizacao sup = bean.getSup();
            // If it have a superior location
            if (sup != null) {
                pstm.setInt(i++, sup.getLocalizacaoid());
            } else {
                pstm.setNull(i++, java.sql.Types.INTEGER);
            }            
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

    /**
     * Load the localization
     * 
     * @param localizacao
     * @return Localizacao
     * @throws DBException
     */
    public Localizacao loadLocalizacao(String localizacao) throws DBException {
        String[] divisao = localizacao.split("\\.");
        
        List<Localizacao> lista = this.doSelect(" AS L "
                + "INNER JOIN Localizacao LS "
                + "ON (L.LocalizacaoSuperiorId = LS.LocalizacaoId) "
                + "WHERE LS.Divisao='"
                + divisao[divisao.length - 2]
                + "' AND L.Divisao='"
                + divisao[divisao.length - 1]
                + "';",
                "L");
        
        String localizacaoPai = "";
        for(int x = 0; x < (divisao.length - 1); x++){
            if(x != 0) {
                localizacaoPai = localizacaoPai + ".";
            }
            localizacaoPai = localizacaoPai + divisao[x];
        }
        for(Localizacao l: lista){
            if(validaSuperiores(localizacaoPai, l.getLocalizacaoid()) == true){
                return l;
            }
        }
        return null;
    }
    
    private boolean validaSuperiores(String localizacao, int localizacaoAtual) throws DBException {
        String[] divisao = localizacao.split("\\.");
        if(divisao.length <= 1){
            return true;
        }
        
        List<Localizacao> lista = this.doSelect(" AS L "
                + "INNER JOIN Localizacao LS "
                + "ON (L.LocalizacaoSuperiorId = LS.LocalizacaoId) "
                + "WHERE LS.Divisao='"
                + divisao[divisao.length - 2]
                + "' AND L.Divisao='"
                + divisao[divisao.length - 1]
                + "' AND L.LocalizacaoId ='+"+ localizacaoAtual +"';",
                "L");
        
        String localizacaoPai = "";
        for(int x = 0; x < (divisao.length - 1); x++){
            if(x != 0) {
                localizacaoPai = localizacaoPai + ".";
            }
            localizacaoPai = localizacaoPai + divisao[x];
        }
        for(Localizacao l: lista){
            if(validaSuperiores(localizacaoPai, l.getLocalizacaoid()) == true){
                return true;
            }
        }
        return false;
    }
    
}
