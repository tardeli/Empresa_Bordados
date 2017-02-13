/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.enumeradores;

/**
 *
 * @author Tardeli
 */
public enum Tamanho {
    TAMANHO_1("Tamanho-1"),
    TAMANHO_2("Tamanho-2"),
    TAMANHO_3("Tamanho-3"),
    TAMANHO_4("Tamanho-4"); 
    
    private final String nome;

    private Tamanho(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
     
}
