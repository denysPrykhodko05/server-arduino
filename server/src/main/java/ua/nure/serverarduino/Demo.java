package ua.nure.serverarduino;

import java.util.Objects;
import ua.nure.serverarduino.models.User;
import ua.nure.serverarduino.service.UserService;
import ua.nure.serverarduino.service.impl.UserServiceImpl;

public class Demo {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user = userService.findUser("asd");
        System.out.println(user);
    }
}
