package nl.getthere.demo.example4;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;

import java.util.List;
import java.util.logging.Logger;

public class MyChatMemory implements ChatMemory {

    Logger logger = Logger.getLogger(MyChatMemory.class.getName());

    @Override
    public void add(String conversationId, List<Message> messages) {
        logger.info("add:");
        for (Message message : messages) {
            logger.info(message.toString());
        }
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        logger.info("get");
        return List.of();
//        return List.of(new UserMessage("Er bestaat maar 1 plaats: Leek"));
    }

    @Override
    public void clear(String conversationId) {

    }
}
