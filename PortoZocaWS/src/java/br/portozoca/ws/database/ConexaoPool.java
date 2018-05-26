/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

/**
 * A class to represent a connection from the pool
 *
 * @author joaovperin
 */
public interface ConexaoPool extends Conexao {

    public void jdbcClose();

}
