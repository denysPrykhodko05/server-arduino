package ua.nure.prykhodko.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ua.nure.prykhodko.dao.GuestsDAO;
import ua.nure.prykhodko.dao.UserDAO;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDAO userDAO = new UserDAO();
        GuestsDAO guestsDAO = new GuestsDAO();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userDAO", userDAO);
        servletContext.setAttribute("guestsDAO", guestsDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
