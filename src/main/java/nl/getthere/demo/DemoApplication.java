package nl.getthere.demo;

import jakarta.annotation.PostConstruct;
import nl.getthere.demo.example1.Example1;
import nl.getthere.demo.example2.Example2;
import nl.getthere.demo.example3.Example3;
import nl.getthere.demo.example4.Example4;
import nl.getthere.demo.tools.Example5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication {

	Logger logger = Logger.getLogger(DemoApplication.class.getName());

	@Autowired private Example1 example1;
	@Autowired private Example2 example2;
	@Autowired private Example3 example3;
	@Autowired private Example4 example4;
	@Autowired private Example5 example5;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void execute() {
		logger.info("-------------------------------");

////		Spring AI: ChatModel
		example1.run();
//
////		Spring AI: ChatClient
//		example2.run();
//
////		Spring AI: Entity mapping
//		example3.run();
//
////		Spring AI: Advisor
//		example4.run();

//		Spring AI: Custom tools
//		example5.run();

		logger.info("-------------------------------");
	}

}
