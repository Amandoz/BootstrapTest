package com.mywebapp.controller;

import com.mywebapp.model.User;
import com.mywebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public UserController() {

    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listController(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "listuser";
    }

    @GetMapping("/createuser")
    public String createUserController(Model model) {
        model.addAttribute("user", new User());
        return "createuser";
    }

    @PostMapping("/createuser")
    public String createUserController(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserController(@PathVariable long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUserController(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edituser";
    }

    @PostMapping("/edit/{id}")
    public String editUserController(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        System.out.println(user.getId());
        return "redirect:/";
    }



}
