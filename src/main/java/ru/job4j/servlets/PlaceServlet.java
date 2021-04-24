package ru.job4j.servlets;

import ru.job4j.model.Ticket;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceServlet extends HttpServlet {

    //private static final Logger LOGGER = Logger.getLogger(PlaceServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        int place=Integer.parseInt(req.getParameter("place"));
//        LOGGER.log(Level.INFO,"place = " + place);
//        Ticket ticket = new Ticket(0, 1, 1, 2, 1);
        try {
            //Ticket ticket = new Ticket(0, 1, 1, 2, 1);
            //PsqlStore.instOf().create(ticket);
            //PsqlStore.instOf().create(new Ticket(0,1,1,2,1));
            PsqlStore.instOf().findAllAccounts();
        } catch (SQLException e) {
            throw new ServletException("Error! SQLException!", e);
        }
        //resp.sendRedirect(req.getContextPath() + "/posts.do");
        //req.getRequestDispatcher("payment.html").forward(req,resp);
    }
}
