package com.mywebapp.controller;

import com.mywebapp.model.Role;
import com.mywebapp.model.User;
import com.mywebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public String listController(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("userNow", user);
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        return "listuser";
    }

    @PostMapping("/createuser")
    public String createUserController(@ModelAttribute("newUser") User user,
                                       @RequestParam(value = "check-create", required = false, defaultValue = "") String check) {
        Set<Role> roles = new HashSet<>();
            if (check.contains("user")) {
                roles.add(new Role(2, "USER"));
            }
            if (check.contains("admin")) {
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

    // Саша если вы читаете этот текст напишите мне, а то я переживаю
    @PostMapping("/edit/{id}")
    public String editUserController(@PathVariable long id,
                                     @RequestParam("first_name") String firstName,
                                     @RequestParam("last_name") String lastName,
                                     @RequestParam("age") int age,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam(value = "check-edit", required = false, defaultValue = "") String check) {
        User user = userService.getUserById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);
        if (!password.isEmpty()) {
            user.setPassword(password);
        }
        Set<Role> roles = new HashSet<>();
        if (check.contains("user")) {
            roles.add(new Role(2, "USER"));
        }
        if (check.contains("admin")) {
            roles.add(new Role(1, "ADMIN"));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/";
    }

}
