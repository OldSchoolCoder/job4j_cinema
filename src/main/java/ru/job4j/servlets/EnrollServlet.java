package ru.job4j.servlets;

import ru.job4j.model.Account;
import ru.job4j.model.Ticket;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class EnrollServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        int row = Integer.parseInt((String) session.getAttribute("row"));
        int cell = Integer.parseInt((String) session.getAttribute("cell"));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        try {
            Account account = new Account(0, name, name + phone + "test@mail.com", phone);
            Ticket ticket = new Ticket(0, 1, row, cell);
            PsqlStore.instOf().create(account, ticket);
        } catch (SQLException e) {
            throw new ServletException("Error! SQLException!", e);
        }
        req.getRequestDispatcher("success.html").forward(req, resp);
    }
}
