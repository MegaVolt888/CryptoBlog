package ru.sorokinkv.CryptoBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sorokinkv.CryptoBlog.validation.PasswordMatches;
import ru.sorokinkv.CryptoBlog.validation.ValidEmail;
import ru.sorokinkv.CryptoBlog.validation.ValidLogin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@PasswordMatches
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidLogin
    @NotNull
    @NotEmpty
    private String username;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String shortId;

//    @NotNull
//    @NotEmpty
//    private UUID user_id;

    public UserDTO(@NotNull @NotEmpty String password, @NotNull @NotEmpty String username, @NotNull @NotEmpty String email, @NotNull @NotEmpty String nickName) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.shortId = nickName;
//        this.user_id = UUID.randomUUID();
    }
}