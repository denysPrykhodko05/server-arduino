package ua.nure.prykhodko.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.prykhodko.dao.GuestsDAO;
import ua.nure.prykhodko.entity.Guests;

@WebServlet("/guests")
public class GuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GuestsDAO guestsDAO = (GuestsDAO) getServletContext().getAttribute("guestsDAO");
        List<Guests> guests = guestsDAO.findAll();
        req.setAttribute("guestList", guests);
        req.getRequestDispatcher("jsp/guests.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
