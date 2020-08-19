package ru.sorokinkv.CryptoBlog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.service.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/{username}")
    public ResponseEntity<Object> user(@PathVariable(name = "username") String username, Principal principal) {
        if (principal!=null) {
            User user = userService.findByName(principal.getName());
            Map<String , String> userMap = new HashMap<>();
            userMap.put("NickName", user.getShortId());
            userMap.put("e-mail", user.getEmail());
            return ResponseEntity.ok(userMap);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).header("error", "user not found").build();
    }

    @GetMapping("/api/user")
    public ResponseEntity<Object> info(Principal principal) {
        if (principal!=null) {
            User user = userService.findByName(principal.getName());
            Map<String , String> userMap = new HashMap<>();
            userMap.put("nickname", user.getShortId());
            userMap.put("email", user.getEmail());
            return ResponseEntity.ok(userMap);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).header("error", "user not found").build();
    }

    @GetMapping("/api/userExist/{shortId}")
    public ResponseEntity<Object> user(@PathVariable(name = "shortId") String shortId) {
            boolean exist = userService.shortIdExists(shortId);
            return ResponseEntity.ok(exist);
    }
}