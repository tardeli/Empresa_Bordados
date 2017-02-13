package br.com.empresabordados.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tardeli
 */
@MappedSuperclass
public class Pessoa extends Domain implements Serializable{ 
    
    @NotEmpty(message = "Cadastre um nome para prosseguir!")
    @Column(length = 30)
    private String nome;
    @Column(length = 12)
    private String telefone;
    @Column(length = 30)
    private String email;
    @Embedded
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco + '}';
    }

}
