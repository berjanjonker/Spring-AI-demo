package nl.getthere.demo.tools;


import org.springframework.ai.chat.model.ToolContext;

import java.util.function.BiFunction;

import static nl.getthere.demo.tools.MockWeatherService.Request;
import static nl.getthere.demo.tools.MockWeatherService.Response;

public class MockWeatherService implements BiFunction<Request, ToolContext, Response> {

    public enum Unit { C, F }
    public record Request(String location) {}
    public record Response(Double temp, Unit unit) {}

//    public Response apply(Request request, ToolContext toolContext) {
//        return switch (request.location) {
//            case "Leek" -> new Response(8.0, Unit.C);
//            case "Madrid" -> new Response(30.0, Unit.C);
//            default -> new Response(null, Unit.C);
//        };
//    }




    public Response apply(Request request, ToolContext toolContext) {
        Unit unit = Unit.valueOf((String) toolContext.getContext().get("unit"));
        return switch (request.location) {
            case "Leek" -> new Response(8.0, unit);
            case "Madrid" -> new Response(30.0, unit);
            default -> new Response(null, unit);
        };
    }
}
