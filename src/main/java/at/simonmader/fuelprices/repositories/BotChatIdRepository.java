package at.simonmader.fuelprices.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import at.simonmader.fuelprices.model.BotChatId;

@Repository
public interface BotChatIdRepository extends JpaRepository<BotChatId, Integer> {

    @Query("select bci.chatId from BotChatId bci")
    List<Integer> findAllChatIds();

}
