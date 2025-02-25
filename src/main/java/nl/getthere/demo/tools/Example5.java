package nl.getthere.demo.tools;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class Example5 {

    Logger logger = Logger.getLogger(Example5.class.getName());

    private final AzureOpenAiChatModel azureOpenAiChatModel;

    public Example5(AzureOpenAiChatModel azureOpenAiChatModel) {
        this.azureOpenAiChatModel = azureOpenAiChatModel;
    }

    public void run() {
        logger.info("Example5 - Tools");
        ChatClient chatClient = ChatClient.builder(azureOpenAiChatModel)
                .build();

        toolCallWithIndirectQuestion(chatClient);

        multipleToolCalls(chatClient);

        toolCallToEntity(chatClient);

//        toolCallWithContextArgument(chatClient);
    }

    private void toolCallWithIndirectQuestion(ChatClient chatClient) {
        String response = chatClient.prompt()
                .user("Wat is de temperatuur in de hoofdstad van Spanje?")
                .tools(new MockWeatherService())
                .call()
                .content();
        logger.info(response);
    }

    private void multipleToolCalls(ChatClient chatClient) {
        String response = chatClient.prompt()
                .user("Wat is de temperatuur in Madrid, Leek en Amsterdam?")
                .tools(new MockWeatherService())
                .call()
                .content();
        logger.info(response);
    }

    private void toolCallToEntity(ChatClient chatClient) {
        Weather weather = chatClient.prompt()
                .user("Wat is de temperatuur in Madrid?")
                .tools(new MockWeatherService())
                .call()
                .entity(Weather.class);
        logger.info(weather.toString());
    }

    private void toolCallWithContextArgument(ChatClient chatClient) {
        String response = chatClient.prompt()
                .user("Wat is de temperatuur in Madrid, Leek en Amsterdam?")
                .tools(new MockWeatherService())
                .toolContext(Map.of("unit", "F"))
                .call()
                .content();
        logger.info(response);
    }
}
