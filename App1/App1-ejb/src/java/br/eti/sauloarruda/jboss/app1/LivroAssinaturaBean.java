package br.eti.sauloarruda.jboss.app1;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sauloarruda
 */
@Stateless(name="livroAssinatura")
public class LivroAssinaturaBean implements LivroAssinatura {


    @PersistenceContext
    private EntityManager em;
    
    public void assinar(String nome) {
        Assinatura assinatura = new Assinatura(nome);
        em.persist(assinatura);
    }
    
    public List<String> assinantes() {
        List<Assinatura> assinaturas = em.
                createQuery("FROM Assinatura ORDER BY id DESC").getResultList();
        List<String> assinaturasStr = new ArrayList<String>();
        for (Assinatura assinatura : assinaturas) {
            assinaturasStr.add(assinatura.getNome());
        }
        return assinaturasStr;
    }
    
}
