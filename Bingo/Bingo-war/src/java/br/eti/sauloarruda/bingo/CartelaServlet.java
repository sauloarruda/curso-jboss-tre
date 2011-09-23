package br.eti.sauloarruda.bingo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartelaServlet extends HttpServlet {

    @EJB
    private Jogo jogo;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Cartela cartela = jogo.buscarCartela(id);
        request.setAttribute("cartela", cartela);
        request.getRequestDispatcher("/WEB-INF/jsp/cartela.jsp")
                .forward(request, response);
    }

}
