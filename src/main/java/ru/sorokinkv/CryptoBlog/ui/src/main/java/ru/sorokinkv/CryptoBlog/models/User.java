package ru.sorokinkv.CryptoBlog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") //"UUID")
    private UUID id;

    @Column(name = "shortid", nullable = false, unique = true)
    private String shortId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<RoleConnection> roleConnections = new ArrayList<>();

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    public User() {
        super();
        this.enabled = false;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private ContactList contactList;

    @Column(name = "messages", nullable = true, unique = true)
    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private VerificationToken token;

}