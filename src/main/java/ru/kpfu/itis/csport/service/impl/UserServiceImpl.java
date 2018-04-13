package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.repository.UserRepository;
import ru.kpfu.itis.csport.service.UserService;
import ru.kpfu.itis.csport.model.SecurityUser;
import ru.kpfu.itis.csport.model.User;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails getUser(String login) {
        User user = userRepository.findByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", login));
        }
        return new SecurityUser(user);
    }
}
