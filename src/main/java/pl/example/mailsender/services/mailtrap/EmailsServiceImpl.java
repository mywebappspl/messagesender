package pl.example.mailsender.services.mailtrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.example.mailsender.services.EmailsService;
import pl.example.mailsender.services.MsgStatusService;

@Service
public class EmailsServiceImpl implements EmailsService {
    private JavaMailSender javaMailSender;
    private MsgStatusService msgStatusService;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailsServiceImpl(final JavaMailSender javaMailSender, final MsgStatusService msgStatusService) {
        this.javaMailSender = javaMailSender;
        this.msgStatusService=msgStatusService;
    }

    @Override
    public String SendMail(final EmailDetails details) {
        System.out.println(details.getMsgId());
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            msgStatusService.SetMsgStatus(details.getMsgId(),3);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            msgStatusService.SetMsgStatus(details.getMsgId(),4);
            return "Error while Sending Mail";
        }
    }
}
