package ua.nure.serverarduino.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.serverarduino.dao.UserDAO;
import ua.nure.serverarduino.dao.impl.UserDAOImpl;
import ua.nure.serverarduino.models.User;
import ua.nure.serverarduino.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User findUser(String id) {
        return userDAO.findById(id);
    }
}
