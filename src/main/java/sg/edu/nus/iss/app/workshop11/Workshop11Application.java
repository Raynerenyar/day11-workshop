package sg.edu.nus.iss.app.workshop11;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import org.slf4j.Logger;

@SpringBootApplication
public class Workshop11Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);

	private static String portNumber = null;
	private static final String DEFAULT_PORT = "8080";

	public static void main(String[] args) {

		for (String arg : args) {
			logger.debug("arg given: " + arg);
			if (arg.contains("--port=")) {
				portNumber = arg.substring(arg.length() - 4, arg.length());
				logger.debug("portNumber: " + portNumber);
			}
		}

		// if portNumber is null, assign port from system env
		if (portNumber == null) {
			portNumber = System.getenv("APP_PORT");
			logger.debug("Sys ENV portNumber : " + portNumber);
		}

		// if portNumber (system env port is null), assign hardcoded default port
		if (portNumber == null) {
			portNumber = DEFAULT_PORT;
		}

		// SpringApplication.run(Workshop11Application.class, args);
		SpringApplication app = new SpringApplication(Workshop11Application.class);

		// set port into spring
		// spring properties are found in
		// https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));

		System.out.printf("%s\n", args[0]);
		System.out.println("Application started on: " + portNumber);

		// run
		// pass args as argument into run()
		app.run(args);
		// this line and the above line will only run after spring runs main twice
		// during the 2 main method calls, the run() and lines after that would not run
		System.out.println("RUNNING");
	}
}
