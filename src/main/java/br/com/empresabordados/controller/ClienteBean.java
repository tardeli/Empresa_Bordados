package br.com.empresabordados.controller;

import br.com.empresabordados.dao.CidadeDao;
import br.com.empresabordados.dao.ClienteDao;
import br.com.empresabordados.dao.EstadoDao;
import br.com.empresabordados.domain.Cidade;
import br.com.empresabordados.domain.Cliente;
import br.com.empresabordados.domain.Estado;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class ClienteBean implements Serializable {

    private Cliente cliente = new Cliente();
    private List<Cliente> listaObjetos;
    private ClienteDao clienteDao = new ClienteDao();
    private String clienteAtivoLabel;

    private EstadoDao estadoDao = new EstadoDao();
    private List<Estado> listaEstados = new ArrayList<>();
    private String estado;
    private boolean verificarEstadoSelecionado;
    private boolean verificarBotaoAddFilho;
    private boolean desabilitarFormCliente;
    private boolean desabilitarFormPedido;
    private boolean situacao;
    private CidadeDao cidadeDao = new CidadeDao();
    private List<Cidade> listaCidadesPorEstado = new ArrayList<>();

    public ClienteBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
        getListaEstados();
        verificarEstadoSelecionado = false;
        situacao = true;
        desabilitarFormCliente = true;
        desabilitarFormPedido = false;
    }

    public void novo() {
        this.cliente = new Cliente();
        verificarEstadoSelecionado = false;
        this.setSituacao(true);
    }

    public void salvar() {
        atualizarSituacao();
        clienteDao.salvarOuAtualizarObjeto(this.cliente);
        getListaObjetos();
        Messages.addGlobalInfo("Salvo com sucesso!");
        novo();
        desabilitarFormCliente = false;
        desabilitarFormPedido = true;
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
        carregarComboboxCidades();
    }

    public void carregarComboboxCidades() {
        this.setEstado(this.cliente.getEndereco().getEstado().getNome());

        if (getEstado() != null && !getEstado().equals("")) {
            this.verificarEstadoSelecionado = true;
            this.listaCidadesPorEstado = cidadeDao.listaCidadesPorEstado(this.cliente.getEndereco().getEstado());
            Messages.addGlobalInfo(this.getEstado());
            Messages.addGlobalInfo("valaor: " + verificarEstadoSelecionado);
        }
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
        if (cliente.getCodigo() == null) {
            verificarBotaoAddFilho = false;
        } else {
            verificarBotaoAddFilho = true;
        }
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

    public List<Estado> getListaEstados() {
        return listaEstados = estadoDao.listarObjetos();
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Cidade> getListaCidadesPorEstado() {
        return listaCidadesPorEstado;
    }

    public void setListaCidadesPorEstado(List<Cidade> listaCidadesPorEstado) {
        this.listaCidadesPorEstado = listaCidadesPorEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isVerificarEstadoSelecionado() {
        return verificarEstadoSelecionado;
    }

    public void setVerificarEstadoSelecionado(boolean verificarEstadoSelecionado) {
        this.verificarEstadoSelecionado = verificarEstadoSelecionado;
    }

    public String getClienteAtivoLabel() {
        if (isSituacao() == true) {
            setClienteAtivoLabel("Ativo");
        } else {
            setClienteAtivoLabel("Inativo");
        }
        return clienteAtivoLabel;
    }

    public void setClienteAtivoLabel(String clienteAtivoLabel) {
        this.clienteAtivoLabel = clienteAtivoLabel;
    }

    public boolean isVerificarBotaoAddFilho() {
        return verificarBotaoAddFilho;
    }

    public void setVerificarBotaoAddFilho(boolean verificarBotaoAddFilho) {
        this.verificarBotaoAddFilho = verificarBotaoAddFilho;
    }

    public boolean isDesabilitarFormCliente() {
        return desabilitarFormCliente;
    }

    public void setDesabilitarFormCliente(boolean desabilitarFormCliente) {
        this.desabilitarFormCliente = desabilitarFormCliente;
    }

    public boolean isSituacao() {
        if(cliente.getCodigo()!=null){
            setSituacao(this.cliente.isSituacao());
        }
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public void atualizarSituacao() {
        this.cliente.setSituacao(situacao);
    }

    public boolean isDesabilitarFormPedido() {
        return desabilitarFormPedido;
    }

    public void setDesabilitarFormPedido(boolean desabilitarFormPedido) {
        this.desabilitarFormPedido = desabilitarFormPedido;
    }
    
}
