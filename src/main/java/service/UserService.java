package service;

import dao.UserDAO;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    public boolean registerUser(User user) {
        //сюда можно добавить дополнительную логику валидации
        if (findUser(user.getLogin(), user.getPassword())!=null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLogin(passwordEncoder.encode(user.getLogin()));
        return userDAO.addUser(user);
    }

    public User findUser(String login, String password) {
        return userDAO.getUsersStream().
                filter(user -> passwordEncoder.matches(login, user.getLogin()) && passwordEncoder.matches(password, user.getPassword())).
                findFirst().
                orElse(null);
    }


}
