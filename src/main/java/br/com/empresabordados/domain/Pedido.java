package br.com.empresabordados.domain;

import br.com.empresabordados.enumeradores.Status;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Pedido")
public class Pedido extends Domain implements Serializable {

    private Cliente cliente;
    private Date dataPedido;
    private String descricaoServico;
    private Funcionario funcionario;
    private Status status;
    private Double total;
    private Date dataEntrega;

    public Pedido() {

    }

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    @NotNull(message = "Selecione um cliente")
    public Cliente getCliente() {
        if (cliente == null) {
            return cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    @ManyToOne
    @JoinColumn(name = "funcionario")
    public Funcionario getFuncionario() {
        if (funcionario == null) {
            return funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    @Override
    public String toString() {
        return "Pedido{" + "cliente=" + cliente + ", dataPedido=" + dataPedido + ", descricaoServico=" + descricaoServico + ", funcionario=" + funcionario + ", status=" + status + ", total=" + total + ", dataEntrega=" + dataEntrega + '}';
    }

}
