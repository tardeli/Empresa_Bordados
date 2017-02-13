/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.controller;

import br.com.empresabordados.dao.EstadoDao;
import br.com.empresabordados.domain.Estado;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class EstadoBean implements Serializable {

    private Estado estado = new Estado();
    private List<Estado> listaObjetos;
    private EstadoDao estadoDao = new EstadoDao();

    public EstadoBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.estado = new Estado();
    }

    public void salvar() {

        estadoDao.salvarOuAtualizarObjeto(this.estado);
        getListaObjetos();
        Messages.addGlobalInfo("Salvo com sucesso!");
        novo();

    }

    public void excluir(Estado c) {
        this.estado = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        estadoDao.deletarObjeto(estado);
        getListaObjetos();
        this.estado = new Estado();
    }

    public void carregarDadosEditar(Estado c) {
        this.estado = c;
    }

    public void imprimir() {
        Map<String, Object> paramentros = new HashMap<>();

        DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("form_estado:dataTable");
        Map<String, Object> filtros = tabela.getFilters();

        String filtroNome = (String) filtros.get("nome");

        if (filtroNome == null) {
            paramentros.put("PESQUISAR_CLIENTE", "%%");
        } else {
            paramentros.put("PESQUISAR_CLIENTE", "%" + filtroNome + "%");
        }

        Connection conexao = HibernateUtil.getConnection();
        String src = Faces.getRealPath("/relatorio/estado.jasper");

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(src, paramentros, conexao);
            //JasperViewer viewer = new JasperViewer(jasperPrint, true);
            //viewer.setVisible(true);

            JasperPrintManager.printReport(jasperPrint, true);
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Tardeli/OneDrive/ProjetoWeb_Inicio/CasadasMarmitasMavem/src/main/webapp/relatorio/RelatorioEstados.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public EstadoDao getEstadoDao() {
        return estadoDao;
    }

    public void setEstadoDao(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListaObjetos() {
        return listaObjetos = estadoDao.listarObjetos();
    }

    public void setListaObjetos(List<Estado> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}
