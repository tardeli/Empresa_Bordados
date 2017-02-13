/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.controller;

import br.com.empresabordados.dao.ClienteDao;
import br.com.empresabordados.dao.Conexao;
import br.com.empresabordados.domain.Cliente;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

    private Cliente cliente = new Cliente();
    private List<Cliente> listaObjetos;
    private ClienteDao clienteDao = new ClienteDao();

    public ClienteBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.cliente = new Cliente();
    }

    public String salvar() {
        if (this.cliente.getCodigo() == null) {
            if (clienteDao.pesquisarCpf(this.cliente.getCpf()) == true) {
                Messages.addGlobalError("Cpf já está cadastrado!");
            } else {
                clienteDao.salvarOuAtualizarObjeto(this.cliente);
                getListaObjetos();
                Messages.addGlobalInfo("Salvo com sucesso!");
                this.cliente = new Cliente();
            }
            return "erro";
        } else {
            clienteDao.salvarOuAtualizarObjeto(cliente);
            getListaObjetos();
            Messages.addGlobalInfo("Atualizado com Sucesso!");
            this.cliente = new Cliente();
            return "erro.xhtml";
        }
    }

    public void excluir(Cliente c) {
        this.cliente = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        clienteDao.deletarObjeto(cliente);
        getListaObjetos();
        this.cliente = new Cliente();
    }

    public void carregarDadosEditar(Cliente c) {
        this.cliente = c;
    }

    public void imprimir() {
        Map<String, Object> paramentros = new HashMap<>();

        DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("form_cliente:dataTable");
        Map<String, Object> filtros = tabela.getFilters();

        String filtroNome = (String) filtros.get("nome");

        if (filtroNome == null) {
            paramentros.put("PESQUISAR_CLIENTE", "%%");
        } else {
            paramentros.put("PESQUISAR_CLIENTE", "%" + filtroNome + "%");
        }

        Connection conexao = HibernateUtil.getConnection();
        String src = Faces.getRealPath("/relatorio/cliente.jasper");

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(src, paramentros, conexao);
            //JasperViewer viewer = new JasperViewer(jasperPrint, true);
            //viewer.setVisible(true);

            JasperPrintManager.printReport(jasperPrint, true);
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Tardeli/OneDrive/ProjetoWeb_Inicio/CasadasMarmitasMavem/src/main/webapp/relatorio/RelatorioClientes.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaObjetos() {
        return listaObjetos = clienteDao.listarObjetos();
    }

    public void setListaObjetos(List<Cliente> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}
