/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.portozoca.ws.entidade;

/**
 * A Bean that represents a product
 *
 * @author joaovperin
 */
public class Lpn {

    private int lpnId;
    private int lpnContenedorId;
    private int produtoId;
    private int localizacaoId;
    private float quantidade;

    public int getLpnId() {
        return lpnId;
    }

    public void setLpnId(int lpnId) {
        this.lpnId = lpnId;
    }

    public int getLpnContenedorId() {
        return lpnContenedorId;
    }

    public void setLpnContenedorId(int lpnContenedorId) {
        this.lpnContenedorId = lpnContenedorId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getLocalizacaoId() {
        return localizacaoId;
    }

    public void setLocalizacaoId(int localizacaoId) {
        this.localizacaoId = localizacaoId;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Lpn{" + "lpnId=" + lpnId + ", lpnContenedorId=" + lpnContenedorId + ", produtoId=" + produtoId + ", localizacaoId=" + localizacaoId + ", quantidade=" + quantidade + '}';
    }

}
