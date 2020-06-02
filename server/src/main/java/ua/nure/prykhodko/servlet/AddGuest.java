package ua.nure.prykhodko.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.prykhodko.dao.GuestsDAO;
import ua.nure.prykhodko.dao.UserDAO;
import ua.nure.prykhodko.entity.Guests;
import ua.nure.prykhodko.entity.User;

@WebServlet("/addGuest")
public class AddGuest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String owner = req.getParameter("owner");
        Guests guests = new Guests();
        User user = new User();
        UserDAO userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
        GuestsDAO guestsDAO = (GuestsDAO) getServletContext().getAttribute("guestsDAO");

        String[] arr = owner.split("\\s");

        user.setName(arr[0]);
        user.setSurname(arr[1]);

        String id = userDAO.getIdByName(user);

        guests.setName(name);
        guests.setSurname(surname);
        guests.setOwner(id);

        Date date = new Date();
        guests.setVisitTime(new Timestamp(date.getTime()));

        guestsDAO.addGuest(guests);
        resp.sendRedirect("/guests");
    }
}
