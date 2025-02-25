package nl.getthere.demo.tools;


import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;

public class MockWeatherService {

    public enum Unit { C, F }
    public record Response(Double temp, Unit unit) {}

    @Tool(description = "Get the weather in location")
    public Response getWeatherForLocation(String location) {
        return switch (location) {
            case "Leek" -> new Response(8.0, Unit.C);
            case "Madrid" -> new Response(30.0, Unit.C);
            default -> new Response(null, Unit.C);
        };
    }

//    @Tool(description = "Get the weather in location")
//    public Response getWeatherForLocationWithToolContext(String location, ToolContext toolContext) {
//        Unit unit = Unit.valueOf((String) toolContext.getContext().get("unit"));
//        return switch (location) {
//            case "Leek" -> new Response(8.0, unit);
//            case "Madrid" -> new Response(30.0, unit);
//            default -> new Response(null, unit);
//        };
//    }
}
