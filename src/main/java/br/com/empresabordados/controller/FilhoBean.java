package br.com.empresabordados.controller;

import br.com.empresabordados.dao.ClienteDao;
import br.com.empresabordados.dao.FilhoDao;
import br.com.empresabordados.domain.Cliente;
import br.com.empresabordados.domain.Filho;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class FilhoBean implements Serializable {

    private Filho filho = new Filho();
    private List<Filho> listaObjetos;
    private FilhoDao filhoDao = new FilhoDao();
    
    @ManagedProperty(value = "#{clienteBean}")
    private ClienteBean clienteBean;
    
    public FilhoBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.filho = new Filho();
    }

    public void salvar() {
        pesquisarCliente(clienteBean.getCliente());
        filhoDao.salvarOuAtualizarObjeto(this.filho);
        getListaObjetos();
        Messages.addGlobalInfo("Salvo com sucesso!");
        novo();
    }

    public void excluir(Filho c) {
        this.filho = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        filhoDao.deletarObjeto(filho);
        getListaObjetos();
         novo();
    }

    public void carregarDadosEditar(Filho c) {
        this.filho = c;
    }
    
    public void pesquisarCliente(Cliente cliente){
        
        ClienteDao clienteDao = new ClienteDao();
        this.filho.setCliente(clienteDao.buscarObjeto(cliente.getCodigo()));
        //Messages.addGlobalInfo(this.filho.getCliente().getNome());
    }

    public void imprimir() {
        Map<String, Object> paramentros = new HashMap<>();

        DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("form_filho:dataTable");
        Map<String, Object> filtros = tabela.getFilters();

        String filtroNome = (String) filtros.get("nome");

        if (filtroNome == null) {
            paramentros.put("PESQUISAR_CLIENTE", "%%");
        } else {
            paramentros.put("PESQUISAR_CLIENTE", "%" + filtroNome + "%");
        }

        Connection conexao = HibernateUtil.getConnection();
        String src = Faces.getRealPath("/relatorio/filho.jasper");

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(src, paramentros, conexao);
            //JasperViewer viewer = new JasperViewer(jasperPrint, true);
            //viewer.setVisible(true);

            JasperPrintManager.printReport(jasperPrint, true);
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Tardeli/OneDrive/ProjetoWeb_Inicio/CasadasMarmitasMavem/src/main/webapp/relatorio/RelatorioFilhos.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public FilhoDao getFilhoDao() {
        return filhoDao;
    }

    public void setFilhoDao(FilhoDao filhoDao) {
        this.filhoDao = filhoDao;
    }

    public Filho getFilho() {
        return filho;
    }

    public void setFilho(Filho filho) {
        this.filho = filho;
    }

    public List<Filho> getListaObjetos() {
        return listaObjetos = filhoDao.listarObjetos();
    }

    public void setListaObjetos(List<Filho> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public ClienteBean getClienteBean() {
        return clienteBean;
    }

    public void setClienteBean(ClienteBean clienteBean) {
        this.clienteBean = clienteBean;
    }
    
    
}
