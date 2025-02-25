package nl.getthere.demo.example1;

import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Example1 {

    Logger logger = Logger.getLogger(Example1.class.getName());

    @Value( "${spring.ai.azure.openai.api-key}" )
    private String azureOpenAiApiKey;

    @Value( "${spring.ai.azure.openai.endpoint}" )
    private String azureOpenAiEndpoint;

    public void run() {
        logger.info("Example1 - Chat Model");
        AzureOpenAiChatModel chatModel = AzureOpenAiChatModel.builder()
            .openAIClientBuilder(new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(azureOpenAiApiKey))
                .endpoint(azureOpenAiEndpoint))
            .defaultOptions(AzureOpenAiChatOptions.builder()
                .deploymentName("gpt-4o-mini")
                .temperature(0.7)
                .build())
            .build();

        String response = chatModel.call("Generate the names of 5 famous pirates.");
        logger.info(response);
    }

}
