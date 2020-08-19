package ru.sorokinkv.CryptoBlog.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sorokinkv.CryptoBlog.dto.UserDTO;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.models.VerificationToken;
import ru.sorokinkv.CryptoBlog.service.UserService;
import ru.sorokinkv.CryptoBlog.validation.EmailExistsException;
import ru.sorokinkv.CryptoBlog.validation.UsernameExistsException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private ApplicationEventPublisher eventPublisher;

    @Qualifier("messageSource")

    @GetMapping(value = "/api/registration")
    public ResponseEntity<User> showRegistrationForm() {
        User user = new User();
        System.out.println(user.toString());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/registration")
    public ResponseEntity<Boolean> registerUserAccount(@Valid @RequestBody UserDTO userDTO) {
        User user = null;
        try {
            user = userService.registerNewUserAccount(userDTO);
        } catch (EmailExistsException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().header("emailExists").build();
        } catch (UsernameExistsException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().header("userExists").build();
        }
//        System.out.println(userJson);
//        JSONParser jsonParser = new JSONParser(userJson);
//        User user = null;
//        try {
//            user = (User) jsonParser.parse();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if(user!=null) {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(userService.findByName(savedUser.getName())!=null);
//        }
//        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/api/registrationConfirm/{token}")
    public ResponseEntity<String> confirmRegistration(@PathVariable String token) {
        String message;
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return ResponseEntity.notFound().build();
        } else message = "accepted";
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return ResponseEntity.ok(message);
    }
}