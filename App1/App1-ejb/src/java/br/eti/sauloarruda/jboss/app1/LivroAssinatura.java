package br.eti.sauloarruda.jboss.app1;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author sauloarruda
 */
@Local
public interface LivroAssinatura {
    
    void assinar(String nome);
    
    List<String> assinantes();
}
