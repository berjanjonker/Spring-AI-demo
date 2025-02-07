package nl.getthere.demo.example4;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class Example4 {

    Logger logger = Logger.getLogger(Example4.class.getName());

    private final AzureOpenAiChatModel azureOpenAiChatModel;

    public Example4(AzureOpenAiChatModel azureOpenAiChatModel) {
        this.azureOpenAiChatModel = azureOpenAiChatModel;
    }

    public void run() {
        logger.info("Example4 - Advisors");
        ChatClient chatClient = ChatClient.builder(azureOpenAiChatModel)
                .build();

        loggerAdvisor(chatClient);

        chatMemoryAdvisor(chatClient);
    }

    private void loggerAdvisor(ChatClient chatClient) {
        Person person = chatClient.prompt()
                .user("Bedenk een persoon")
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .entity(Person.class);
        logger.info(person.toString());
    }

    private void chatMemoryAdvisor(ChatClient chatClient) {
        Person person = chatClient.prompt()
                .user("Bedenk een persoon")
                .advisors(new MessageChatMemoryAdvisor(new MyChatMemory()))
                .call()
                .entity(Person.class);
        logger.info(person.toString());
    }

}
