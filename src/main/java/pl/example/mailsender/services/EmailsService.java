package pl.example.mailsender.services;

import pl.example.mailsender.services.mailtrap.EmailDetails;

public interface EmailsService {
    String SendMail(EmailDetails details);
}
