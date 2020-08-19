package ru.sorokinkv.CryptoBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinkv.CryptoBlog.models.Role;
import ru.sorokinkv.CryptoBlog.models.RoleStatus;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByStatus(RoleStatus status);
}