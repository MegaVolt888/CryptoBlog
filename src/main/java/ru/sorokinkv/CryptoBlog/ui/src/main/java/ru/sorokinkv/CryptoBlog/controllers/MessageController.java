package ru.sorokinkv.CryptoBlog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sorokinkv.CryptoBlog.dto.MessageDTO;
import ru.sorokinkv.CryptoBlog.models.Message;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.service.MessageService;
import ru.sorokinkv.CryptoBlog.validation.EmailExistsException;
import ru.sorokinkv.CryptoBlog.validation.UsernameExistsException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/api/message")
    public ResponseEntity<Object> getAllMessage() {
        try {
            List<Message> messageList = messageService.getAll();
            return ResponseEntity.accepted().body(messageList);
        }catch (NullPointerException exception) {
            return ResponseEntity.accepted().body(new Message());
        }
    }
    @GetMapping("/api/message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(value = "id") String id) {

        Message message = messageService.getMessageByShortId(id);
        if (message != null) {
            return ResponseEntity.ok().body(message);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/message/exist/{id}")
    public ResponseEntity<Boolean> existMessageById(@PathVariable(value = "id") String id) {

       return ResponseEntity.ok(messageService.existMessageByShortId(id));
    }

    @PostMapping(value = "/api/message")
    public ResponseEntity<Message> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
            Message message = null;
             if(!messageService.existMessageByShortId(messageDTO.getShortId())) {
                message = messageService.createNewMessage(messageDTO);
                return ResponseEntity.accepted().body(messageService.getMessageByShortId(message.getShortId()));
            } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("error","shortId is exist").build();
    }
}
