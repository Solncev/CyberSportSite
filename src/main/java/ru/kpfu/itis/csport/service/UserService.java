package ru.kpfu.itis.csport.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.csport.model.User;

public interface UserService {
    UserDetails getUser(String login);
    User getUserByEmail(String email);
    void save(User user);
}
