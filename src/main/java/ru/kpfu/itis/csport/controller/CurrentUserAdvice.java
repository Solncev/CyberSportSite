package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.UserService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:48 PM
 *
 * Injects current into models of those controllers that need it
 */
@ControllerAdvice(annotations = {AuthController.class})
public class CurrentUserAdvice {

    private UserService userService;

    @Autowired
    public CurrentUserAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "currentUser")
    public User currentUser() {
        return userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
