package ru.sorokinkv.CryptoBlog.service;

import ru.sorokinkv.CryptoBlog.dto.RoleDTO;
import ru.sorokinkv.CryptoBlog.dto.UserDTO;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.models.VerificationToken;
import ru.sorokinkv.CryptoBlog.validation.EmailExistsException;
import ru.sorokinkv.CryptoBlog.validation.UsernameExistsException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User saveUser(@NotNull User user);

    List<User> getAllUsers();

    void removeUserByShortId(@NotNull String id);

    void removeUserById(@NotNull String id);

    User getUserById(@NotNull String id);

    User findByName(@NotNull String username);

    @Transactional
    User registerNewUserAccount(@NotNull UserDTO accountDto) throws EmailExistsException, UsernameExistsException;

    boolean emailExists(@NotNull String email);

    boolean usernameExists(@NotNull String username);

    boolean shortIdExists(@NotNull String id);

    User getUser(@NotNull String verificationToken);

    VerificationToken getVerificationToken(@NotNull String verificationToken);

    void saveRegisteredUser(@NotNull User user);

    List<User> getContacts(@NotNull User user);

    void createVerificationToken(User user, String token);

    @Transactional
    User updateRoles(@NotNull RoleDTO roleDTO, @NotNull User user);

    void addContact(@NotNull User owner, @NotNull User friend);


}
