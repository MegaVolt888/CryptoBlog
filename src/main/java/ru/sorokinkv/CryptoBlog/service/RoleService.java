package ru.sorokinkv.CryptoBlog.service;

import ru.sorokinkv.CryptoBlog.models.Role;
import ru.sorokinkv.CryptoBlog.models.RoleStatus;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface RoleService {
    void createRole(@NotNull Role role);

    Role getRoleById(@NotNull String id);

    Role getRoleByStatus(@NotNull RoleStatus status);

    List<Role> getAllRoles();

    void removeAllRoles();

    void removeRoleById(@NotNull String id);

    void removeRole(@NotNull Role role);
}
