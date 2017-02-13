package br.com.empresabordados.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Funcionario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Funcionario extends PessoaFisica{
    @Column(length = 15)
    private String usuario;
    @Column(length = 10)
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "usuario=" + usuario + ", senha=" + senha + '}';
    }

    
}
