/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.utils;

import br.portozoca.ws.encrypt.Base64;
import br.portozoca.ws.encrypt.Criptography;
import br.portozoca.ws.encrypt.Md5;
import br.portozoca.ws.encrypt.Rot13;

/**
 * A classe to help with encryptation
 *
 * @author joaovperin
 */
public final class CryptUtils {

    /**
     * Returns a new Rot13 cryptography class
     *
     * @return Criptography
     */
    public static Criptography rot13() {
        return new Rot13();
    }

    /**
     * Returns a new Md5 cryptography class
     *
     * @return Criptography
     */
    public static Criptography md5() {
        return new Md5();
    }

    /**
     * Returns a new Base64 cryptography class
     *
     * @return Criptography
     */
    public static Criptography base64() {
        return new Base64();
    }

}
