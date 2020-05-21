package ua.nure.serverarduino.service;

import org.springframework.stereotype.Service;
import ua.nure.serverarduino.models.User;

@Service
public interface UserService {
    User findUser(String id);
}
