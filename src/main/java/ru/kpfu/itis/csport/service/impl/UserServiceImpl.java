package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.csport.model.SecurityUser;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.repository.UserRepository;
import ru.kpfu.itis.csport.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails getUser(String login) {
        User user = userRepository.findByUsername(login);
        if (user == null) {
            return null;
        }
        return new SecurityUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    public User findUser(String login) {
        User user = userRepository.findByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", login));
        }
        return user;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        User second = userRepository.findByUsername(user.getUsername());
        if (second != null && second.getId() != user.getId()) {
            return false;
        }
        userRepository.updateUser(user.getUsername(), user.getEmail(), user.getId());
        return true;
    }

    @Override
    public boolean changePassword(User user, String newPassword, String oldPassword) {
        User requiredUser = userRepository.findByUsername(user.getUsername());
        if (!encoder.matches(oldPassword, requiredUser.getPassword())) {
            return false;
        }
        userRepository.changePassword(encoder.encode(newPassword), user.getId());
        return true;
    }
}
