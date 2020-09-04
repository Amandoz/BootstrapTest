package com.mywebapp.controller;

import com.mywebapp.model.Role;
import com.mywebapp.model.User;
import com.mywebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    public AdminController() {

    }

    @Autowired
    public AdminController(UserService userService) {
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
    public String createUserController(@ModelAttribute("user") User user,
                                       @RequestParam(value = "userCheck", required = false) boolean userCheck,
                                       @RequestParam(value = "adminCheck", required = false) boolean adminCheck) {
        Set<Role> roles = new HashSet<>();
        if (userCheck) {
            roles.add(new Role(2, "USER"));
        }
        if (adminCheck) {
            roles.add(new Role(1, "ADMIN"));
        }
        user.setRoles(roles);
        userService.saveUser(user);

        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserController(@PathVariable long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String editUserController(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles().toString());
        return "edituser";
    }

    @PostMapping("/edit/{id}")
    public String editUserController(@ModelAttribute("user") User user,
                                     @RequestParam(value = "userCheck", required = false) boolean userCheck,
                                     @RequestParam(value = "adminCheck", required = false) boolean adminCheck) {
        user.setRoles(null);
        Set<Role> roles = new HashSet<>();
        if (adminCheck) {
            roles.add(new Role(1, "ADMIN"));
        }
        if (userCheck) {
            roles.add(new Role(2, "USER"));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/";
    }

}
