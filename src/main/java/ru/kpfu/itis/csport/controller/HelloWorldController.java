package ru.kpfu.itis.csport.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/12/18 10:44 PM
 */
@Controller
public class HelloWorldController {

    @GetMapping(path = "/hello")
    @ResponseBody
    public String helloWorld() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "Hello, " + user.getUsername();
    }
}
