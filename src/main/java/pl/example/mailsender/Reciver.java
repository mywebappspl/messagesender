package pl.example.mailsender;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.example.mailsender.model.Message;
import pl.example.mailsender.services.EmailsService;
import pl.example.mailsender.services.FechMsgFromDB;
import pl.example.mailsender.services.mailtrap.EmailDetails;
import pl.example.virtualoffice.virtualoffice.model.projection.MessageForRabbit;

@Component

public class Reciver {
    FechMsgFromDB fetchMsgFromDB;
    EmailsService emailsService;
    Reciver(final FechMsgFromDB fetchMsgFromDB,final EmailsService emailsService) {
        this.fetchMsgFromDB = fetchMsgFromDB;
        this.emailsService=emailsService;
    }
    @RabbitListener(queues="vo_messages")
    public void geMsg(MessageForRabbit msg){

        try{
            Message message = fetchMsgFromDB.ComposeMsgFromDatabase(msg.getId());
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setMsgId(message.getId());
            emailDetails.setRecipient(message.getMember().getEmail());
            emailDetails.setSubject("message from messaging system");
            emailDetails.setMsgBody(message.getContent().getContent());
            emailsService.SendMail(emailDetails);

        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }

    }

}
