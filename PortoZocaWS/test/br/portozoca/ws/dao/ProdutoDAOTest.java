/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.dao;

import br.portozoca.ws.database.Conexao;
import br.portozoca.ws.database.ConexaoFactory;
import br.portozoca.ws.entidade.Produto;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author programacao
 */
public class ProdutoDAOTest {

    /**
     * Test of select method, of class ProdutoDAO.
     */
    @Test
    public void testSelect() throws Exception {
        List<Produto> lista;
        try (Conexao cn = ConexaoFactory.query()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
            lista = dao.select();
        }
        // Asserts it will get the auto-incremented key
        lista.forEach((produto) -> {
            assertNotNull(produto.getDescricao());
        });
        assertTrue(lista.size() > 0);
    }

    /**
     * Test of insert method, of class ProdutoDAO.
     */
    @Test
    public void testInsert() throws Exception {
        Produto p = new Produto();
        try (Conexao cn = ConexaoFactory.transaction()) {
            p.setProdutoId(0);
            p.setDescricao("descricao teste");
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
            dao.insert(p);
        }
        // Asserts it will get the auto-incremented key
        int idProduto = p.getProdutoId();
        assertNotEquals(0, idProduto);
    }

    /**
     * Test of update method, of class ProdutoDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        Produto p;
        String newDesc = "descricao teste novaxxx";
        // Select a random product
        try (Conexao cn = ConexaoFactory.query()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
            p = dao.select().get(0);
        }
        // Change it's description
        try (Conexao cn = ConexaoFactory.transaction()) {
            p.setDescricao(newDesc);
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
            dao.update(p);

            p = dao.select().get(0);

            assertEquals(newDesc, p.getDescricao());
        }

    }

    /**
     * Test of delete method, of class ProdutoDAO.
     */
    @Test
    public void testDelete() throws Exception {
        Produto p;
        int size;
        // Select a random product
        try (Conexao cn = ConexaoFactory.query()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
            List<Produto> list = dao.select();
            size = list.size();
            p = list.get(0);
        }
        // Delete this product and see if the list shrinked
        try (Conexao cn = ConexaoFactory.transaction()) {
            DataAccessObject<Produto> dao = DAOFactory.create(Produto.class, cn);
            dao.delete(p);

            List<Produto> list = dao.select();
            assertEquals(size - 1, list.size());
        }

    }

}
