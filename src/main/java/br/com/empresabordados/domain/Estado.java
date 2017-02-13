package br.com.empresabordados.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Estado")
public class Estado extends Domain implements Serializable{
    @NotEmpty(message = "Cadastre um nome para prosseguir!")
    @Column(length = 20)
    private String nome;
    @NotEmpty(message = "Cadastre uma sigla para prosseguir!")
    @Column(length = 2)
    private String sigla;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Estado{" + "nome=" + nome + ", sigla=" + sigla + '}';
    }
    
    
}
