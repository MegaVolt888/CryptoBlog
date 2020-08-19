package ru.sorokinkv.CryptoBlog.service;

import ru.sorokinkv.CryptoBlog.dto.MessageDTO;
import ru.sorokinkv.CryptoBlog.models.Message;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MessageService {

    List<Message> getAll();

    Message createNewMessage(@NotNull MessageDTO messageDTO);

    boolean existMessageByShortId(@NotNull String id);

    Message getMessageByShortId(@NotNull String id);
}
