package pl.example.mailsender.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.mailsender.model.Message;
import pl.example.mailsender.repository.MessageRepository;

@AllArgsConstructor
@Service
public class FechMsgFromDB {
    private MessageRepository messageRepository;
    public Message composeMsgFromDatabase(int msgToMemberId){
        return messageRepository.findMessageBy_Id(msgToMemberId);
    }
}
