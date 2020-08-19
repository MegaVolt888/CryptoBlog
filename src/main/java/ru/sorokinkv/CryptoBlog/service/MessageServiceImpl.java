package ru.sorokinkv.CryptoBlog.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sorokinkv.CryptoBlog.dto.MessageDTO;
import ru.sorokinkv.CryptoBlog.models.Message;
import ru.sorokinkv.CryptoBlog.repositories.MessageRepository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Message createNewMessage(@NotNull MessageDTO messageDTO) {
        Message message = new Message();
//        message.setId(UUID.randomUUID());
        message.setText(messageDTO.getText());
        message.setShortId(messageDTO.getShortId());
        message.setDescription(messageDTO.getDescription());
        message.setReceiver_id(messageDTO.getReceiverId());
        message.setDate(new Date(System.currentTimeMillis()));
        return messageRepository.save(message);
    }

    @Override
    public boolean existMessageByShortId(@NotNull String id) {
        try{
            return messageRepository.existsByShortId(id);
        } catch (Exception e) {
            return false;
        }
    }

    public Message getMessageByShortId(@NotNull String id) {
        if (messageRepository.existsByShortId(id)) {
            return messageRepository.findByShortId(id);
        } else return null;
    }

}