package ru.sorokinkv.CryptoBlog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
public class VerificationToken {

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @Id
    @Column(name = "token_id", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(length = 200)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    @JsonIgnore
    @ToString.Exclude
    private User user;

}