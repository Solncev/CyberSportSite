package ru.kpfu.itis.csport.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails getUser(String login);
}
