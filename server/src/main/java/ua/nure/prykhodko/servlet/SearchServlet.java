package ua.nure.prykhodko.servlet;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.prykhodko.dao.UserDAO;
import ua.nure.prykhodko.entity.User;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("SameSite", "None");
        resp.addCookie(cookie);
        String id = req.getParameter("id");
        ServletContext servletContext = getServletContext();
        UserDAO userDAO = (UserDAO) servletContext.getAttribute("userDAO");
        String uidEncode = Base64.getEncoder().encodeToString(id.getBytes());
        User user = userDAO.findById(uidEncode);
        req.setAttribute("user", user);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
