package nl.getthere.demo.tools;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.BiFunction;
@Configuration
public class Config {

    @Bean
    @Description("Get the weather in location")
    public BiFunction<MockWeatherService.Request, ToolContext, MockWeatherService.Response> currentWeather() {
        return new MockWeatherService();
    }
}
