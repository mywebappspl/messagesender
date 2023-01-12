package pl.example.mailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.example.mailsender.model.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(nativeQuery=true, value="SELECT t.id, t.member_id, t.message_id, t.msg_status, m.content , e.email FROM taken_for t INNER JOIN messages m ON t.message_id=m.id INNER JOIN members e ON t.member_id=e.id WHERE t.id=:id")
    Message findMessageBy_Id(int id);
    <Optional> Message findById(int msgId);
    Message save(Message message);
}
