package ru.job4j.servlets;

import ru.job4j.model.Ticket;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class PlaceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String place = req.getParameter("place");
        String row = Character.toString(place.charAt(0));
        String cell = Character.toString(place.charAt(1));
        HttpSession session = req.getSession();
        session.setAttribute("row", row);
        session.setAttribute("cell", cell);
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }
}
