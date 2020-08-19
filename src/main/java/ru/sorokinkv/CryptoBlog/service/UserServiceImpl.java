package ru.sorokinkv.CryptoBlog.service;

//import org.apache.commons.compress.utils.IOUtils;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sorokinkv.CryptoBlog.dto.RoleDTO;
import ru.sorokinkv.CryptoBlog.dto.UserDTO;
import ru.sorokinkv.CryptoBlog.models.*;
import ru.sorokinkv.CryptoBlog.repositories.RoleRepository;
import ru.sorokinkv.CryptoBlog.repositories.UserRepository;
import ru.sorokinkv.CryptoBlog.repositories.VerificationTokenRepository;
import ru.sorokinkv.CryptoBlog.validation.EmailExistsException;
import ru.sorokinkv.CryptoBlog.validation.UsernameExistsException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    final VerificationTokenRepository tokenRepository;
    @Autowired
    ContactServiceImpl contactServiceImpl;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public User saveUser(@NotNull User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void removeUserByShortId(@NotNull String id) { userRepository.deleteByShortId(id); }

    public void removeUserById(@NotNull String id) {
        userRepository.deleteById(id);
    }

     public User getUserById(@NotNull String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public User findByName(@NotNull String username) {
        return userRepository.findByName(username);
    }

    @Transactional
    public User registerNewUserAccount(@NotNull UserDTO accountDto) throws EmailExistsException, UsernameExistsException {

        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException("message.regError", "email");
        }
        if (usernameExists(accountDto.getUsername())) {
            throw new UsernameExistsException("message.regUsernameError", "username");
        }

        User user = new User();
        user.setName(accountDto.getUsername());
        String encode = passwordEncoder.encode(accountDto.getPassword());
        user.setPassword(encode);
        user.setEmail(accountDto.getEmail());
        RoleConnection roleConnection = new RoleConnection();
        Role role = roleRepository.findByStatus(RoleStatus.USER);
        roleConnection.setRole(role);
        user.getRoleConnections().add(roleConnection);
        user.setShortId(accountDto.getShortId());
//        user.setId(accountDto.getgetUser_id());
        User savedUser = saveUser(user);
        return savedUser;
    }

    public boolean emailExists(@NotNull String email) {
        try {
            User user = userRepository.findByEmail(email);
            return user != null;
        } catch (NullPointerException e){
            return false;
        }

    }
    public boolean usernameExists(@NotNull String username) {
        try {
            User user = userRepository.findByName(username);
            return user != null;
        } catch (NullPointerException e){
            return false;
        }
    }

    public boolean shortIdExists(@NotNull String id) {
        try {
            User user = userRepository.findByShortId(id);
            return user != null;
        } catch (NullPointerException e){
            return false;
        }
    }

    public User getUser(@NotNull String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    public VerificationToken getVerificationToken(@NotNull String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    public void saveRegisteredUser(@NotNull User user) {
        userRepository.save(user);
    }

    public List<User> getContacts(@NotNull User user) { return  userRepository.findContactById(user.getId());}

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Transactional
    public User updateRoles(@NotNull RoleDTO roleDTO, @NotNull User user) {
        user.getRoleConnections().clear();
        if (roleDTO.getIsAdmin() != null && roleDTO.getIsAdmin()) {
            user.getRoleConnections().add(new RoleConnection(roleRepository.findByStatus(RoleStatus.ADMIN)));
        }
        user.getRoleConnections().add(new RoleConnection(roleRepository.findByStatus(RoleStatus.USER)));
        return user;
    }

    public void addContact(@NotNull User owner, @NotNull User friend) {
        ContactList contactList =  owner.getContactList();
        List<User> users= contactList.getUsers();
        users.add(friend);
        contactList.setUsers(users);
        contactServiceImpl.saveContact(contactList);
    }
}