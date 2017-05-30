package br.com.empresabordados.controller;

import br.com.empresabordados.dao.CidadeDao;
import br.com.empresabordados.dao.EstadoDao;
import br.com.empresabordados.domain.Cidade;
import br.com.empresabordados.domain.Estado;
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
public class CidadeBean implements Serializable {

    private Cidade cidade = new Cidade();
    private List<Cidade> listaObjetos;
    private CidadeDao cidadeDao = new CidadeDao();
    
    private List<Estado> listaEstados;
    private EstadoDao estadoDao = new EstadoDao();

    public CidadeBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.cidade = new Cidade();
    }

    public void salvar() {
        cidadeDao.salvarOuAtualizarObjeto(this.cidade);
        getListaObjetos();
        Messages.addGlobalInfo("Salvo com sucesso!");
        novo();
    }

    public void excluir(Cidade c) {
        this.cidade = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        cidadeDao.deletarObjeto(cidade);
        getListaObjetos();
         novo();
    }

    public void carregarDadosEditar(Cidade c) {
        this.cidade = c;
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaObjetos() {
        return listaObjetos = cidadeDao.listarObjetos();
    }

    public void setListaObjetos(List<Cidade> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Estado> getListaEstados() {
        return listaEstados = estadoDao.listarObjetos();
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }
    
    
    
}
