/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.ConexaoFactory;
import br.portozoca.ws.database.DBException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author programacao
 */
public class DAOFactoryTest {

    @Test
    public void testCreate() throws DBException {
        try (Conexao conn = ConexaoFactory.query()) {
            DataAccessObject<TestEntity> create = DAOFactory.create(TestEntity.class, conn);
            assertEquals(GenericDAO.class, create.getClass());
        }
    }

    private class TestEntity {
    }

}
