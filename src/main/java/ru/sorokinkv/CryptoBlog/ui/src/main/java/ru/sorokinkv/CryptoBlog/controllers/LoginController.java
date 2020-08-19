package ru.sorokinkv.CryptoBlog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/api/login")
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping("/api/accessDenied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

    @GetMapping("/api/principal-info")
    public String showPrincipalInfoPage() {
        return "principal-info";
    }

}
