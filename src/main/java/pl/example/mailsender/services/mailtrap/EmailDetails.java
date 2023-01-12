package pl.example.mailsender.services.mailtrap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
        private int msgId;
        private String recipient;
        private String msgBody;
        private String subject;
}
