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
        if (GeraIdLocalizacao.newId == 0){
            // Deve ler o maior ID gerado no banco de dados
            GeraIdLocalizacao.newId = 1;
        }
        this.id = GeraIdLocalizacao.getNewId();
        this.division = divisao;
        this.sup = null;
    }
    
    public Localizacao(int id) {
        this.id = id;
        
        //Ler e carregar dados do banco de dados
        
    }
    
    private Localizacao(String divisao, Localizacao sup) {
        if (GeraIdLocalizacao.newId == 0){
            // Deve ler o maior ID gerado no banco de dados e atribuir à newId
            GeraIdLocalizacao.newId = 1;
        }
        this.id = GeraIdLocalizacao.getNewId();
        this.division = divisao;
        this.sup = sup;
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
    
    //Classe criada para gerar ID sequêncial
    private static class GeraIdLocalizacao {
        public static int newId = 0;
        
        public static int getNewId() {
            return ++newId;
        }
    }
} 