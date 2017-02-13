/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tardeli
 */
@Embeddable
public class Endereco implements Serializable{
    @Column(length = 60)
    private String rua;
    @Column(length = 20)
    private String bairro;
    @Column(length = 9)
    private String cep;
    @Column(length = 50)
    private String complemento;
    @Column(length = 150)
    private String pontoReferencia;
    @ManyToOne
    @JoinColumn(name = "cidade", nullable = false)
    @NotNull(message = "Selecione uma cidade!")
    private Cidade cidade;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", bairro=" + bairro + ", cep=" + cep + ", complemento=" + complemento + ", pontoReferencia=" + pontoReferencia + ", cidade=" + cidade + '}';
    }

}
