/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.sauloarruda.bingo;

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
public class BingoServlet extends HttpServlet {

    @EJB(name="jogo")
    private Jogo jogo;

    /**
     * /bingo/new
     * /bingo/:id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("novo".equals(request.getPathInfo().substring(1))) {
            novo(request, response);
        } else if ("sortear".equals(request.getPathInfo().substring(1))) {
            sortear(request, response);
        } 
        else {
            index(request, response);
        }
    }

    private void novo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bingo bingo = jogo.novoJogo();
        response.sendRedirect(request.getContextPath() + "/bingo/?id=" + bingo.getId());
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("bingo", buscarBingo(request));
        request.getRequestDispatcher("/bingo.jsp").forward(request, response);
    }

    private void sortear(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        BingoNumero numero = jogo.sortearNumero(id);
        if (numero == null) {
          request.setAttribute("bingo", jogo.buscarJogo(id));
        } else {
        request.setAttribute("numero", numero.getNumero());
        request.setAttribute("bingo", numero.getBingo());
        }
        request.getRequestDispatcher("/bingo.jsp").forward(request, response);
    }
    
    private Bingo buscarBingo(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        return jogo.buscarJogo(id);
    }
}