package ru.sorokinkv.CryptoBlog.validation;

public class UsernameExistsException extends ExistsException {

    public UsernameExistsException(final String message, final String fieldError) {
        super(message, fieldError);
    }
}