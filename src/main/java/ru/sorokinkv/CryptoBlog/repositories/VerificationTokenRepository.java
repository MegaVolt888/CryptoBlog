package ru.sorokinkv.CryptoBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.models.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
