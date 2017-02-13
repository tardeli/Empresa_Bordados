package br.com.empresabordados.dao;

import br.com.empresabordados.domain.Funcionario;
import br.com.empresabordados.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class FuncionarioDao extends Generic_Dao<Funcionario> implements Serializable {

    private Session sessao;

    public Funcionario autenticarLogin(String Usuario, String Senha) {
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Funcionario.class);
            criteria.add(Restrictions.eq("usuario", Usuario));
            criteria.add(Restrictions.eq("senha", Senha));
            Funcionario funcionario = (Funcionario) criteria.uniqueResult();
            return funcionario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sessao.close();
        }
    }
}
