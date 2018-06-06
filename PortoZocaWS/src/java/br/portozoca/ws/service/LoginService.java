/*
 * PortoZocaWS
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.portozoca.ws.service;

import br.portozoca.ws.entidade.Usuario;

/**
 * Descrição da classe.
 */
public class LoginService {

    public static Usuario autentica(String usuario, String senha) {
        if(usuario!=null && usuario.equalsIgnoreCase("adalberto")){
            Usuario usr = new Usuario();
            usr.setApelido(usuario);
            usr.setSenha(senha);
            return usr;
        }
        
        return null;
//        try (Conexao conn = ConexaoFactory.query()) {
//            DataAccessObject<Usuario> dao = DAOFactory.create(Usuario.class, conn);
//            // retorna o usuaario sem validar a senha (sim, é intencional)
//            Usuario userObject = dao.selectOne("WHERE Login = '" + usuario + "'");
//            return userObject;
//        } catch (DBException ex) {
//            return null;
//        }
    }

}
