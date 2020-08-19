package ru.sorokinkv.CryptoBlog.service;

import ru.sorokinkv.CryptoBlog.models.ContactList;

import javax.validation.constraints.NotNull;

public interface ContactService {
    void saveContact(@NotNull ContactList contactList);
}
