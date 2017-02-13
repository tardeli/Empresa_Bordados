package br.com.empresabordados.domain;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author Tardeli
 */
@MappedSuperclass
public class PessoaJuridica extends Pessoa{
    
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
