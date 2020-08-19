package ru.sorokinkv.CryptoBlog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinkv.CryptoBlog.models.ContactList;
import ru.sorokinkv.CryptoBlog.repositories.ContactRepository;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;

    public void saveContact(@NotNull ContactList contactList) {
        contactRepository.save(contactList);
    }

}
