/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.dao;

import br.com.empresabordados.domain.Cidade;
import br.com.empresabordados.domain.Estado;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class CidadeDao extends Generic_Dao<Cidade> implements Serializable {

    private Session sessao;
    public List<Cidade> listaCidadesPorEstado(Estado estado) {
        List<Cidade> listaCidades = new ArrayList<>();
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            return sessao.getNamedQuery("pesquisarCidadesPorEstados").setParameter("estado", estado).list();
        } catch (Exception e) {        
            e.getMessage();
            return null;
        }finally{
            sessao.close();
        }     
    }
    
    //mesma funcao que a de cima usando criteria
     public List<Cidade> listarObjetosCriterios(){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sessao.createCriteria(Cidade.class);
            criteria.createCriteria ("estado", "e");
            
            criteria.add(Restrictions.eq("e.sigla", "SC"));
            //criteria.add(Restrictions.eq("situacao", Situacao.Ativo));
            //criteria.addOrder(Order.asc("t.nome"));
            //criteria.addOrder(Order.asc("nomeGuerra"));
            
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
}
