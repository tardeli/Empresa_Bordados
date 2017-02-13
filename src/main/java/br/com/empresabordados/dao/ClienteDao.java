package br.com.empresabordados.dao;

import br.com.empresabordados.domain.Cliente;
import br.com.empresabordados.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class ClienteDao extends Generic_Dao<Cliente>{
    private Session sessao;   
    
    public Boolean pesquisarCpf(String Cpf){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Cliente.class);
            criteria.add(Restrictions.ilike("cpf", Cpf));
            Cliente cliente = (Cliente) criteria.uniqueResult();
            if(cliente==null){
                return false;
            }else{
                return true;
            }         
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            sessao.close();
        } 
    }
}
