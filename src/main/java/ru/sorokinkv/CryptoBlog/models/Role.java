package ru.sorokinkv.CryptoBlog.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @Column(name = "role_id", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(length = 20)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleStatus status = RoleStatus.USER;
}