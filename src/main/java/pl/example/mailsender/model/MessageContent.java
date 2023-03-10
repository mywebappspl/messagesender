package pl.example.mailsender.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Messages")
public class MessageContent {
    @Id
    private Integer id;
    private String content;
   }
