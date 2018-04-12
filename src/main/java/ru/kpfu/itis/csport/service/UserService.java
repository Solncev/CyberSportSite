package ru.kpfu.itis.csport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.SecurityUser;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.repository.UserRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/12/18 10:11 PM
 */
@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", s));
        }
        return new SecurityUser(user);
    }
}
