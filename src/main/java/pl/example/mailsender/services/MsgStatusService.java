package pl.example.mailsender.services;

import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.example.mailsender.exceptions.CustomExceptionHandler;
import pl.example.mailsender.exceptions.ExceptionMessages;
import pl.example.mailsender.model.Message;
import pl.example.mailsender.repository.MessageRepository;

import java.util.Optional;

@Service
public class MsgStatusService {
    private final MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);
    public MsgStatusService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void setMsgStatus(int msgId, int status) {
        try {
           Optional<Message> msg =  messageRepository.findById(msgId);
           if(msg.isPresent())
               msg.get().setMsgStatus(status);
           else
               throw new CustomExceptionHandler(ExceptionMessages.ID_NOT_EXIST);
           messageRepository.save(msg.get());
        } catch (Exception e) {
            logger.error("status for message is"+msgId + "cannot be saved");
        }
    }
}
