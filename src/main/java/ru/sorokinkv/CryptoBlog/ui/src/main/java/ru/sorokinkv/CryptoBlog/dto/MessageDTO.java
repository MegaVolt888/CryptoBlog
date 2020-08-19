package ru.sorokinkv.CryptoBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class MessageDTO {

    @NotNull
    @NotEmpty
    private String shortId;

    @NotNull
    @NotEmpty
    private String text;

    private String receiverId;

    private String description;


    public MessageDTO(@NotNull @NotEmpty String shortId, @NotNull @NotEmpty String text, String receiverId, String description) {
        this.shortId = shortId;
        this.text = text;
        this.receiverId = receiverId;
        this.description = description;
    }

}
