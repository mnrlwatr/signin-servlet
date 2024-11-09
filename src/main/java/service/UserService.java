package service;

import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private static final Set<User> usersSet = new HashSet<>();

    public boolean registerUser(User user) {
        //сюда можно добавить дополнительную логику валидации
        if (findUser(user.getLogin(), user.getPassword())!=null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLogin(passwordEncoder.encode(user.getLogin()));
        return usersSet.add(user);
    }

    public boolean deleteUser(User user) {
        return usersSet.remove(user);
    }

    public User findUser(String login, String password) {
        return usersSet.stream().
                filter(user -> passwordEncoder.matches(login, user.getLogin()) && passwordEncoder.matches(password, user.getPassword())).
                findFirst().
                orElse(null);
    }


}
