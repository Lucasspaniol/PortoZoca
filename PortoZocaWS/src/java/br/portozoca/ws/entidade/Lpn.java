/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.entidade;

/**
 * A Bean that represents a product
 *
 * @author Jonas
 */
public class Lpn {

    private int lpnId;
    private Produto produto;
    private float quantidade;
    private Lpn lpnContenedor;
    private Localizacao localizacao;

    public int getLpnId() {
        return lpnId;
    }

    public void setLpnId(int lpnId) {
        this.lpnId = lpnId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Lpn getLpnContenedor() {
        return lpnContenedor;
    }

    public void setLpnContenedor(Lpn lpnContenedor) {
        this.lpnContenedor = lpnContenedor;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Lpn{" + "lpnId=" + lpnId + ", produto=" + produto + ", quantidade=" + quantidade + ", lpnContenedor=" + lpnContenedor + ", localizacao=" + localizacao + '}';
    }

}
