package ua.nure.serverarduino.dao;

import org.springframework.stereotype.Repository;
import ua.nure.serverarduino.models.User;

@Repository
public interface UserDAO {

    User findById(String id);
}
