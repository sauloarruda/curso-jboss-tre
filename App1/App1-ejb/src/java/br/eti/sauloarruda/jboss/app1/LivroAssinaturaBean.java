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

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getState() {
     return 1;
    }

    public String getStateString() {
       return "OK";
    }

    public void jbossInternalLifecycle(String string) throws Exception {
        
    }

    public void create() throws Exception {
        
    }

    public void destroy() {
        
    }

    public void start() throws Exception {
        
    }

    public void stop() {
        
    }
    
}
