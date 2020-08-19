package ru.sorokinkv.CryptoBlog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sorokinkv.CryptoBlog.dto.RoleDTO;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.service.ContactService;
import ru.sorokinkv.CryptoBlog.service.UserService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @RequestMapping("/")
    public String user() {
        return "admin/main-panel";
    }

    @RequestMapping("/users")
    public String profile() {
        return "admin/users";
    }

    @GetMapping(value = "/allUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/all-users";
    }

    @GetMapping(value = {"/user-edit"})
    public String personEdit(@RequestParam("id") String userId, Map<String, Object> model) {
        final User user = userService.getUserById(userId);
        model.put("user", user);
        model.put("role", new RoleDTO());
        return "admin/user-edit";
    }

    @PostMapping(value = {"/user-save"})
    public String personSave(@ModelAttribute("user") User frontUser, @ModelAttribute("role") RoleDTO roleDTO) {
        User user = userService.getUserById(frontUser.getId().toString());
        user.setEmail(frontUser.getEmail());
        user = userService.updateRoles(roleDTO, user);
        userService.saveUser(user);
        return "redirect:allUsers";
    }

    @GetMapping(value = {"/user-remove"})
    public String personRemove(@RequestParam("id") String userId) {
        User user = userService.getUserById(userId);
        userService.removeUserById(userId);
        return "redirect:allUsers";
    }
}