/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.DBException;
import br.portozoca.ws.entidade.Localizacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Spaniol
 */
public class LocalizacaoDAO  extends GenericDAO<Localizacao> implements DataAccessObject<Localizacao> {

    /** Select Statement */
    private static final String SQL_SELECT = "SELECT ProdutoId, Referencia, Descricao, Observacao FROM Produto";
    /**
     * Constructor
     *
     * @param conn
     */
    public LocalizacaoDAO(Conexao conn) {
        super(conn, Localizacao.class);
    }

    /**
     * Select method
     *
     * @return List
     * @throws DBException
     */
    @Override
    public final List<Localizacao> select() throws DBException {
        List<Localizacao> list = new ArrayList<>();
        try (ResultSet rs = conn.createStmt().executeQuery(SQL_SELECT)) {
            while (rs.next()) {
                Localizacao l = new Localizacao();
                l.setId(rs.getInt(1));
                l.setDivision(rs.getString(2));
                Localizacao sup;
                l.setSup(sup = new Localizacao(rs.getInt(1)));
                list.add(l);
            }
        } catch (DBException | SQLException e) {
            throw new DBException(e);
        }
        return list;
    }

}
