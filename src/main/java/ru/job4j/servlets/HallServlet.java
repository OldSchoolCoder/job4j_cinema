package ru.job4j.servlets;

import ru.job4j.model.Ticket;
import ru.job4j.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("name");
        int row = Integer.parseInt(Character.toString(id.charAt(0)));
        int cell = Integer.parseInt(Character.toString(id.charAt(1)));
        List<Integer> bookingCells;
        try {
            bookingCells = PsqlStore.instOf().findAllCell(row);
        } catch (SQLException e) {
            throw new ServletException("Error! SQLException!", e);
        }
        boolean isBook = bookingCells.contains(cell);
        if (isBook) {
            writer.print("<input type=\"radio\" name=\"place\" disabled=\"true\"> Ряд " + row + " Место " + cell);
        } else {
            writer.print("<input type=\"radio\" name=\"place\" value=\"" + row + cell + "\"> Ряд " + row + " Место " + cell);
        }
        writer.flush();
    }
}
