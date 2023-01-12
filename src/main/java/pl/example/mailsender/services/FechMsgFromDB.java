package pl.example.mailsender.services;

import org.springframework.stereotype.Service;
import pl.example.mailsender.model.Message;
import pl.example.mailsender.repository.MessageRepository;

@Service
public class FechMsgFromDB {
    MessageRepository messageRepository;

    public FechMsgFromDB(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message ComposeMsgFromDatabase(int msgToMemberId){
        return messageRepository.findMessageBy_Id(msgToMemberId);
    }
}
