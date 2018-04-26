package ru.kpfu.itis.csport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.csport.model.User;
import ru.kpfu.itis.csport.service.UserService;

@Controller
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile")
    public String getProfilePage(Model model, @RequestParam(value = "userAlreadyExistError", required = false) String userAlreadyExistError,
                                 @RequestParam(value = "wrongPassword", required = false) String wrongPassword) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("currentUser", currentUser);
        if (wrongPassword != null) {
            model.addAttribute("wrongPassword", wrongPassword);
        }
        if (userAlreadyExistError != null) {
            model.addAttribute("userAlreadyExistError", userAlreadyExistError);
        }
        return "profile";
    }

    @PostMapping(value = "/profile/edit")
    @Transactional
    public String updateUser(Model model, @RequestParam("newusername") String newUsername,
                             @RequestParam("email") String newEmail) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);
        if (userService.updateUser(currentUser)) {
            UserDetails userDetails = userService.getUser(newUsername);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            return "redirect:/profile";
        }
        return "redirect:/profile?userAlreadyExistError=true";
    }


    @PostMapping(value = "/profile/changePassword")
    public String changePassword(@RequestParam("newPassword") String newPassword,
                                 @RequestParam("oldPassword") String oldPassword) {
        User currentUser = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userService.changePassword(currentUser, newPassword, oldPassword)) {
            return "redirect:/profile";
        }
        return "redirect:/profile?wrongPassword=true";
    }
}
