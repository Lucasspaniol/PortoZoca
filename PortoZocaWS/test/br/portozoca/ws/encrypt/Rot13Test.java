/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.encrypt;

import br.portozoca.ws.utils.CryptUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Rot13 Encrypting/Decrypting
 *
 * @author joaovperin
 */
public class Rot13Test {

    /** Encriptor instance */
    private final Criptography instance = CryptUtils.rot13();

    /**
     * Test of encrypt method, of class Rot13.
     */
    @Test
    public void testEncrypt() {
        String input = "portozoca.encript.rot13";
        assertEquals("cbegbmbpn.rapevcg.ebg13", instance.encrypt(input));
    }

    /**
     * Test of decrypt method, of class Rot13.
     */
    @Test
    public void testDecrypt() {
        String input = "grfgr";
        assertEquals("teste", instance.decrypt(input));
    }

}
