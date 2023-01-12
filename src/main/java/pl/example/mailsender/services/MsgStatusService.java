package pl.example.mailsender.services;

import org.springframework.stereotype.Service;
import pl.example.mailsender.model.Message;
import pl.example.mailsender.repository.MessageRepository;

@Service
public class MsgStatusService {
    private MessageRepository messageRepository;

    public MsgStatusService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void SetMsgStatus(int msgId, int status) {
        try {
           Message msg =  messageRepository.findById(msgId);
           msg.setMsg_status(status);
           messageRepository.save(msg);
        } catch (Exception e) {
            System.out.println("status cannot be save");
        }
    }
}
