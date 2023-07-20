package at.simonmader.fuelprices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bot_chat_ids")
public class BotChatId {

    @Id
    @Column(name = "chat_id")
    Integer chatId;

    public BotChatId() {

    }

    public BotChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getChatId() {
        return this.chatId;
    }

}
