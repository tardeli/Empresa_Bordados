package br.com.empresabordados.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Cidade")
@NamedQuery(name = "pesquisarCidadesPorEstados", query = "FROM Cidade c WHERE c.estado = :estado")
public class Cidade extends Domain implements Serializable{
    @NotNull(message = "Selecione um estado")
    @ManyToOne
    @JoinColumn(name = "estado")
    private Estado estado;
    
    @NotEmpty(message = "Cadastre uma cidade para prosseguir!")
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
