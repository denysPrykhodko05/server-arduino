package ua.nure.prykhodko.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import ua.nure.prykhodko.dao.GuestsDAO;

@WebServlet("/deleteGuest")
public class DeleteGuest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        GuestsDAO guestsDAO = (GuestsDAO) getServletContext().getAttribute("guestsDAO");

        if (StringUtils.isEmpty(id)) {
            resp.sendRedirect("/guests");
        }

        guestsDAO.deleteById(Integer.parseInt(id));
        resp.sendRedirect("/guests");
    }
}
