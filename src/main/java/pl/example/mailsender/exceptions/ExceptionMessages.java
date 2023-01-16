package pl.example.mailsender.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {
    ID_NOT_EXIST ("Id not exists"),
    CANNOT_FETCH_FROM_RABBIT ("Meesage cannot be fetched from Rabbit");
    private final String message;
}
