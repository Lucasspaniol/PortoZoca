/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.database;

/**
 *
 * @author joaovperin
 */
public class DBException extends Exception {

    public DBException(String string) {
        super(string);
    }

    public DBException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DBException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

}
