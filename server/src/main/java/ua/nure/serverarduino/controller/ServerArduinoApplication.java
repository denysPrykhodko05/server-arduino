package ua.nure.serverarduino.controller;

import java.util.Objects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.serverarduino.dao.UserDAO;
import ua.nure.serverarduino.dao.impl.UserDAOImpl;
import ua.nure.serverarduino.models.User;

@SpringBootApplication
@RestController
public class ServerArduinoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerArduinoApplication.class, args);
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam(value = "id", defaultValue = "") String uid) {
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.findById(uid);
        return Objects.isNull(user) ? "f" : "t";
    }

}
