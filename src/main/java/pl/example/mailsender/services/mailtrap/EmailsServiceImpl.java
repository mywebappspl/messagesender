package pl.example.mailsender.services.mailtrap;

import lombok.AllArgsConstructor;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.example.mailsender.services.EmailsService;
import pl.example.mailsender.services.MsgStatusService;

@Service
@AllArgsConstructor
public class EmailsServiceImpl implements EmailsService {
    private static final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);
    private final JavaMailSender javaMailSender;
    private final MsgStatusService msgStatusService;
    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public void sendMail(final EmailDetails details) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            msgStatusService.setMsgStatus(details.getMsgId(),3);
        }
        catch (Exception e) {
            msgStatusService.setMsgStatus(details.getMsgId(),4);
            logger.error("message with id"+details.getMsgId()+"cannot be send");
        }
    }
}
