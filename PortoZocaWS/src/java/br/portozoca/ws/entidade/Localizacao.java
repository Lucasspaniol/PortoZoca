/*
 *
 */
package br.portozoca.ws.entidade;

/**
 *
 * @author Spaniol
 */
public class Localizacao {
    private int id;
    private String division;
    private Localizacao sup;
    
    public Localizacao(String divisao) {
        this.division = divisao;
        this.sup = null;
    }
    
    public Localizacao(int id) {
        this.id = id;
        
        //Ler e carregar dados do banco de dados
        
    }
    
    private Localizacao(String divisao, Localizacao sup) {
        this.division = divisao;
        this.sup = sup;
    }

    public Localizacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String divisao) {
        this.division = divisao;
    }

    public Localizacao getSup() {
        return sup;
    }

    public void setSup(Localizacao sup) {
        this.sup = sup;
    }
    
    //Cria filho da estrutura
    public Localizacao createDivision(String division) {
        Localizacao divisao = new Localizacao(division);
        return divisao;
    }
}