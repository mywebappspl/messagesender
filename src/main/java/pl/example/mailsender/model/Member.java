package pl.example.mailsender.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Members")
public class Member {
    @Id
    private Integer id;
    private String email;
}
