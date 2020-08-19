package ru.sorokinkv.CryptoBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinkv.CryptoBlog.models.RoleStatus;
import ru.sorokinkv.CryptoBlog.models.User;
import ru.sorokinkv.CryptoBlog.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        try {
            User user = userRepository.findByName(name);
            if (user == null) {
                throw new UsernameNotFoundException("Нет пользователя с таким именем: " + name);
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    user.getEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRoleConnections().stream()
                            .map(roleConnection -> roleConnection.getRole().getStatus())
                            .collect(Collectors.toList())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<GrantedAuthority> getAuthorities(List<RoleStatus> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleStatus role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        }
        return authorities;
    }
}