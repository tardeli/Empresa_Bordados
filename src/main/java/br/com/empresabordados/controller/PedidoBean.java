package br.com.empresabordados.controller;

import br.com.empresabordados.dao.ClienteDao;
import br.com.empresabordados.dao.PedidoDao;
import br.com.empresabordados.domain.Cliente;
import br.com.empresabordados.domain.Pedido;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
public class PedidoBean implements Serializable {

    private Pedido pedido = new Pedido();
    private List<Pedido> listaObjetos;
    private PedidoDao pedidoDao = new PedidoDao();
    
    private List<Cliente> listaClientes = new ArrayList<>();
    private ClienteDao clienteDao = new ClienteDao();
    

    public PedidoBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
        getListaClientes();
    }

    public void novo() {
        this.pedido = new Pedido();
    }

    public void salvar() {
        pedidoDao.salvarOuAtualizarObjeto(this.pedido);
        getListaObjetos();
        Messages.addGlobalInfo("Salvo com sucesso!");
        novo();
    }

    public void excluir(Pedido c) {
        this.pedido = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        pedidoDao.deletarObjeto(pedido);
        getListaObjetos();
         novo();
    }

    public void carregarDadosEditar(Pedido c) {
        this.pedido = c;
    }

    public void imprimir() {
        Map<String, Object> paramentros = new HashMap<>();

        DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("form_pedido:dataTable");
        Map<String, Object> filtros = tabela.getFilters();

        String filtroNome = (String) filtros.get("nome");

        if (filtroNome == null) {
            paramentros.put("PESQUISAR_CLIENTE", "%%");
        } else {
            paramentros.put("PESQUISAR_CLIENTE", "%" + filtroNome + "%");
        }

        Connection conexao = HibernateUtil.getConnection();
        String src = Faces.getRealPath("/relatorio/pedido.jasper");

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(src, paramentros, conexao);
            //JasperViewer viewer = new JasperViewer(jasperPrint, true);
            //viewer.setVisible(true);

            JasperPrintManager.printReport(jasperPrint, true);
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Tardeli/OneDrive/ProjetoWeb_Inicio/CasadasMarmitasMavem/src/main/webapp/relatorio/RelatorioPedidos.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getListaObjetos() {
        return listaObjetos = pedidoDao.listarObjetos();
    }

    public void setListaObjetos(List<Pedido> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes = clienteDao.listarObjetos();
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }  
}
