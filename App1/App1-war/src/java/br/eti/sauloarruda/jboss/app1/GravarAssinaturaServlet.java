package br.eti.sauloarruda.jboss.app1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sauloarruda
 */
public class GravarAssinaturaServlet extends HttpServlet {

    @EJB(name="livroAssinatura")
    private LivroAssinatura livroAssinatura;
    
    /**
     * /gravar-assinaturas
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        livroAssinatura.assinar(request.getParameter("nome"));
        doGet(request, response);
    }
    
    /**
     * /assinaturas
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("assinaturas", livroAssinatura.assinantes());
        request.getRequestDispatcher("/assinaturas.jsp").forward(request, response);    

    }
 
}
