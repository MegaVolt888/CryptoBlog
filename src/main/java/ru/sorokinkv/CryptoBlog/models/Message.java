package ru.sorokinkv.CryptoBlog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "message_id", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "shortid", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  shortId;

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "save_date")
    private Date date;

    @Column(name = "receiverid")
    private String receiver_id;

    @Column(name = "sender_description")
    private String description;

}
