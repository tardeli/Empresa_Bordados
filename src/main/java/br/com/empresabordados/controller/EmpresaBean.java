/*H
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.controller;

import br.com.empresabordados.dao.EmpresaDao;
import br.com.empresabordados.domain.Empresa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class EmpresaBean implements Serializable {

    private Empresa empresa = new Empresa();
    private List<Empresa> listaObjetos;
    private EmpresaDao empresaDao = new EmpresaDao();

    public EmpresaBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.empresa = new Empresa();
    }

    public String salvar() {
        if (this.empresa.getCodigo() == null) {
            empresaDao.salvarOuAtualizarObjeto(this.empresa);
            getListaObjetos();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.empresa = new Empresa();    
            return "erro";
        } else {
            empresaDao.salvarOuAtualizarObjeto(empresa);
            getListaObjetos();
            Messages.addGlobalInfo("Atualizado com Sucesso!");
            this.empresa = new Empresa();
            return "erro.xhtml";
        }
    }

    public void excluir(Empresa c) {
        this.empresa = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        empresaDao.deletarObjeto(empresa);
        getListaObjetos();
        this.empresa = new Empresa();
    }

    public void carregarDadosEditar(Empresa c) {
        this.empresa = c;
    }

    public EmpresaDao getEmpresaDao() {
        return empresaDao;
    }

    public void setEmpresaDao(EmpresaDao empresaDao) {
        this.empresaDao = empresaDao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaObjetos() {
        return listaObjetos = empresaDao.listarObjetos();
    }

    public void setListaObjetos(List<Empresa> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}
