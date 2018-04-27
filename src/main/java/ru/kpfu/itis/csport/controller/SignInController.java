package ru.kpfu.itis.csport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.csport.util.AuthForm;

@Controller
public class SignInController {

    @RequestMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error,
                               @RequestParam(value = "registrationSuccessful", required = false) String registrationSuccessfulMessage,
                            Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        if (registrationSuccessfulMessage != null) {
            model.addAttribute("registrationSuccessfulMessage", registrationSuccessfulMessage);
        }
        model.addAttribute("authForm", new AuthForm());
        return "login";
    }

    @GetMapping(value = "/")
    public String getDefaultPage() {
        return "redirect:/login";
    }
}