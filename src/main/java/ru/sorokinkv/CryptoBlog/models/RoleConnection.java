package ru.sorokinkv.CryptoBlog.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role_connection")
@Data
@NoArgsConstructor
public class RoleConnection {

    @Id
    @Column(name = "role_connection_id", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @ColumnDefault("-1")
    private Role role;

    public RoleConnection(Role role){
        this.role = role;
    }
}