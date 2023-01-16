package pl.example.mailsender.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomExceptionHandler extends RuntimeException{
    private String exception;
    public CustomExceptionHandler(ExceptionMessages exceptionMessages)
    {
        this.exception = exceptionMessages.getMessage();
    }
}
