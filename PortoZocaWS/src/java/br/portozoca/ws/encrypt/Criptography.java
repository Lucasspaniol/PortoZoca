/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.encrypt;

/**
 * An interface to group all cryptography classes
 *
 * @author joaovperin
 */
public interface Criptography {

    public String encrypt(String input);

    public String encrypt(String input, String key);

    public String decrypt(String input);

    public String decrypt(String input, String key);

}
