/*
 *
 */
package br.portozoca.ws.entidade;

/**
 *
 * @author Spaniol
 */
public class Localizacao {
    private int localizacaoid;
    private String division;
    private Localizacao sup;
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public int getLocalizacaoid() {
        return localizacaoid;
    }

    public void setLocalizacaoid(int localizacaoid) {
        this.localizacaoid = localizacaoid;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Localizacao getSup() {
        return sup;
    }

    public void setSup(Localizacao sup) {
        this.sup = sup;
    }
    
    @Override
    public String toString() {
        return "Produto{" + "localizacaoid=" + localizacaoid + ", division=" + division + ", sup=" + sup.getDivision();
    }
}