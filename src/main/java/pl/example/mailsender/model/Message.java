package pl.example.mailsender.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="taken_for")
public class Message {
    @Id
    private Integer id;
    private Integer msgStatus;
    @OneToOne
    @JoinColumn(name = "message_id")
    private MessageContent content;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
