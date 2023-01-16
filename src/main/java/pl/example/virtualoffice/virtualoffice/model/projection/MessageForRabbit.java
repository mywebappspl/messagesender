package pl.example.virtualoffice.virtualoffice.model.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class MessageForRabbit implements Serializable {
    private int id;
    private int messageId;
    private int memberId;
}
