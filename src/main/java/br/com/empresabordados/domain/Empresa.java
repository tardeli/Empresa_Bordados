package br.com.empresabordados.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Empresa")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Empresa extends PessoaJuridica{
   
   
}
