package nl.getthere.demo.example3;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class Example3 {

    Logger logger = Logger.getLogger(Example3.class.getName());

    private final AzureOpenAiChatModel azureOpenAiChatModel;

    public Example3(AzureOpenAiChatModel azureOpenAiChatModel) {
        this.azureOpenAiChatModel = azureOpenAiChatModel;
    }

    public void run() {
        logger.info("Example2 - Output -> entity");
        ChatClient chatClient = ChatClient.builder(azureOpenAiChatModel)
                .build();

        llmOutputToEntity(chatClient);

        llmOutputToListOfEntities(chatClient);

    }

    private void llmOutputToEntity(ChatClient chatClient) {
        Person person = chatClient.prompt()
                .user("Bedenk een persoon")
                .call()
                .entity(Person.class);
        logger.info(person.toString());
    }

    private void llmOutputToListOfEntities(ChatClient chatClient) {
        List<BookClub> bookClubs = chatClient.prompt()
                .user("30 chinese boekenclubs")
                .call()
                .entity(new ParameterizedTypeReference<>() {});
        bookClubs.forEach(club -> logger.info(club.toString()));
    }

}
