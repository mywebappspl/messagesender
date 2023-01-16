package pl.example.mailsender;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.example.mailsender.exceptions.CustomExceptionHandler;
import pl.example.mailsender.exceptions.ExceptionMessages;
import pl.example.mailsender.model.Message;
import pl.example.mailsender.services.EmailsService;
import pl.example.mailsender.services.FechMsgFromDB;
import pl.example.mailsender.services.mailtrap.EmailDetails;
import pl.example.virtualoffice.virtualoffice.model.projection.MessageForRabbit;

@Component
@AllArgsConstructor
public class Reciver {
    private FechMsgFromDB fetchMsgFromDB;
    private EmailsService emailsService;
    @RabbitListener(queues="vo_messages")
    public void getMsgForRabbit(MessageForRabbit msg){

        try{
            Message message = fetchMsgFromDB.composeMsgFromDatabase(msg.getId());
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setMsgId(message.getId());
            emailDetails.setRecipient(message.getMember().getEmail());
            emailDetails.setSubject("message from messaging system");
            emailDetails.setMsgBody(message.getContent().getContent());
            emailsService.sendMail(emailDetails);
        }
        catch (Exception e)
        {
            throw new CustomExceptionHandler(ExceptionMessages.CANNOT_FETCH_FROM_RABBIT);
        }
    }

}
