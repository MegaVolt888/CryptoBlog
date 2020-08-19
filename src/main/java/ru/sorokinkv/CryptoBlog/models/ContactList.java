package ru.sorokinkv.CryptoBlog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class ContactList {

    @Id
    @Column(name = "contact_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false, unique = true)
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> users;
}
