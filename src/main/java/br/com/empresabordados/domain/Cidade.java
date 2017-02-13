package br.com.empresabordados.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Cidade")
public class Cidade extends Domain implements Serializable{
    @ManyToOne
    @JoinColumn(name = "estado", nullable = false)
    @NotNull(message = "Selecione um estado")
    private Estado estado;
    @NotEmpty(message = "Cadastre um nome para prosseguir!")
    @Column(length = 30)
    private String nome;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cidade{" + "estado=" + estado + ", nome=" + nome + '}';
    }
    
    
}
