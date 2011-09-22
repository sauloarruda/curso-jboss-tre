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
public class CartelaServlet extends HttpServlet {

    @EJB(name = "jogo")
    private Jogo jogo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getRequestURI().endsWith("nova")) {
            nova(request, response);
        } else {
            index(request, response);
        }

    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Cartela cartela = jogo.buscarCartela(id);
        request.setAttribute("cartela", cartela);
        request.getRequestDispatcher("/cartela.jsp").forward(request, response);
    }
    
    private void nova(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer bingoId = Integer.valueOf(request.getParameter("id"));
        Cartela cartela = jogo.novaCartela(bingoId);
        response.sendRedirect(request.getContextPath() 
                + "/cartela/?id=" + cartela.getId());
    }
}
