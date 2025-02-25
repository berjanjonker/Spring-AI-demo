package nl.getthere.demo.example2;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class Example2 {

    Logger logger = Logger.getLogger(Example2.class.getName());

    private final AzureOpenAiChatModel azureOpenAiChatModel;

    public Example2(AzureOpenAiChatModel azureOpenAiChatModel) {
        // no need to create the model yourself.
        // Spring AI will create the model for you based on the given application.properties
        this.azureOpenAiChatModel = azureOpenAiChatModel;
    }

    public void run() {
        logger.info("Example2 - Chat Client");
        ChatClient chatClient = ChatClient.builder(azureOpenAiChatModel).build();

        simpleChatExample(chatClient);

        chatWithCustomInstructions(chatClient);

        chatWithPreviousMessages(chatClient);
    }

    private void simpleChatExample(ChatClient chatClient) {
        String response = chatClient.prompt()
                .user("Hoi, wie ben je?")
                .call()
                .content();
        logger.info(response);
    }

    private void chatWithCustomInstructions(ChatClient chatClient) {
        String response = chatClient.prompt()
                .system("Je praat als een piraat.")
                .user("Hoi, wie ben je?")
                .call()
                .content();
        logger.info(response);
    }

    private void chatWithPreviousMessages(ChatClient chatClient) {
        UserMessage userMessage = new UserMessage("Het regent buiten, stom he.");
        AssistantMessage assistantMessage = new AssistantMessage("""
                Ja, dat kan inderdaad vervelend zijn! Regen kan soms de plannen in de war schoppen.
                Maar het heeft ook zijn voordelen, zoals het geven van water aan de planten en het creëren van een gezellige sfeer binnen.
                Wat doe je graag als het regent?
                """);

        String response = chatClient.prompt()
            .messages(List.of(userMessage, assistantMessage))
            .user("ik ga naar buiten, wat denk je zal ik een een paraplu meenemen?")
            .call()
            .content();
        logger.info(response);
    }

}
