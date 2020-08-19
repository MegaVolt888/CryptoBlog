package ru.sorokinkv.CryptoBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sorokinkv.CryptoBlog.models.Role;
import ru.sorokinkv.CryptoBlog.models.RoleStatus;
import ru.sorokinkv.CryptoBlog.repositories.RoleRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    RoleRepository roleRepository;

    public void createRole(@NotNull Role role) {
        roleRepository.save(role);
    }

    public Role getRoleById(@NotNull String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByStatus(@NotNull RoleStatus status) {
        return roleRepository.findByStatus(status);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void removeAllRoles() {
        roleRepository.deleteAll();
    }

    public void removeRoleById(@NotNull String id) {
        roleRepository.deleteById(id);
    }

    public void removeRole(@NotNull Role role) {
        roleRepository.delete(role);
    }
}