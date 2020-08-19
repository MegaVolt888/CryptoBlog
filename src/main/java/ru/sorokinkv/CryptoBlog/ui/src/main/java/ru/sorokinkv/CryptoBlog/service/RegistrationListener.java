package ru.sorokinkv.CryptoBlog.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.sorokinkv.CryptoBlog.models.OnRegistrationCompleteEvent;
import ru.sorokinkv.CryptoBlog.models.User;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private static final Logger log = LogManager.getLogger(RegistrationListener.class);
    UserServiceImpl userServiceImpl;
    @Qualifier("messageSource")
    MessageSource messages;
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${custom.main.domain}")
    private String domain;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userServiceImpl.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Подтверждение регистрации";
        String confirmationUrl = "/registrationConfirm/" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(username);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Пройдите по ссылке для подтверждения email " + domain + confirmationUrl);
        mailSender.send(email);
        log.info("Отправлено письмо для подтверждения на почту: {}", recipientAddress);
    }
}