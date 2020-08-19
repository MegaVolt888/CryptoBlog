package ru.sorokinkv.CryptoBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sorokinkv.CryptoBlog.models.ContactList;

public interface ContactRepository extends JpaRepository<ContactList, Long> {

    ContactList findByUsersId(String name);

    boolean existsById(String name);
}
