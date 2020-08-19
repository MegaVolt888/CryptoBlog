package ru.sorokinkv.CryptoBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sorokinkv.CryptoBlog.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByShortId(String name);

    boolean existsByShortId(String name);
}
