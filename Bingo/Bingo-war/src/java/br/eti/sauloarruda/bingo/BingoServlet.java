package br.eti.sauloarruda.bingo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BingoServlet extends HttpServlet {

    @EJB
    private Jogo jogo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Bingo bingo = jogo.buscarBingo(id);
        request.setAttribute("bingo", bingo);
        request.getRequestDispatcher("/WEB-INF/jsp/bingo.jsp")
                .forward(request, response);
    }

}
