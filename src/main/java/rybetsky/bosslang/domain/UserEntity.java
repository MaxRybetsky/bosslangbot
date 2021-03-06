package rybetsky.bosslang.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rybetsky.bosslang.bot.states.StatesIdentifiers;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "usr")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    private Long chatId;

    private String firstName;
    private String lastName;
    private String username;

    @Enumerated
    @Column(columnDefinition = "int")
    private StatesIdentifiers state;

    @Enumerated
    @Column(columnDefinition = "int")
    private Language language;

    public UserEntity(Long chatId, String firstName,
                      String lastName, String username,
                      StatesIdentifiers state, Language language) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.state = state;
        this.language = language;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", state=" + state +
                ", language=" + language +
                '}';
    }
}
