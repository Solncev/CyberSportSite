package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.UserService;
import ru.kpfu.itis.csport.util.UserForm;
import ru.kpfu.itis.csport.util.UserFormToUserTransformer;

import javax.validation.Valid;
import java.util.function.Function;


@Controller
public class SignUpController {
    private final UserService userService;
    private final Function<UserForm, User> userFormToUserTransformer;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
        this.userFormToUserTransformer = new UserFormToUserTransformer();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") @Valid UserForm userForm,
                                BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()){
            if (userService.getUser(userForm.getUsername()) != null) {
                model.addAttribute("error", "username_error");
            }
            else if (userService.getUserByEmail(userForm.getEmail()) != null){
                model.addAttribute("error", "email_error");
            }
            else {
                userService.save(userFormToUserTransformer.apply(userForm));
                return "redirect:/login";
            }
        }
        return "registration";
    }
}