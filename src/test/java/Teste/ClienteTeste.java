/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.empresabordados.dao.CidadeDao;
import br.com.empresabordados.dao.EstadoDao;
import br.com.empresabordados.domain.Cidade;
import br.com.empresabordados.domain.Estado;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class ClienteTeste {
    @Test
    public void testarPesquisaCidades(){
        EstadoDao estadoDao = new EstadoDao();
        Estado estado = estadoDao.buscarObjeto(1L);
        
        System.out.println(estado);
        
        
        CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> cidades = new ArrayList<>();
        cidades = cidadeDao.listaCidadesPorEstado(estado);
        
        System.out.println(cidades);
    }
}
