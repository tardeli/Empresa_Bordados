package br.com.empresabordados.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Filho")
public class Filho extends Domain implements Serializable {
    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    @NotNull(message = "Selecione uma cliente!")
    private Cliente cliente;
    @NotEmpty(message = "Cadastre um nome para prosseguir!")
    @Column(length = 50)
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Filho{" + "cliente=" + cliente + ", nome=" + nome + ", dataNascimento=" + dataNascimento + '}';
    }

}
