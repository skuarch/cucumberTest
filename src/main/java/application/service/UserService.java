package application.service;

import application.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User("the name", "123"));
        }

        return users;

    }

}
