package ru.sorokinkv.CryptoBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinkv.CryptoBlog.models.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User save(User user);

    User findByShortId(String id);

    User findByName(String name);

    User findById(UUID uuid);

    List<User> findContactById(UUID userId);

    void deleteByShortId(String id);

    User findByEmail(String email);
}