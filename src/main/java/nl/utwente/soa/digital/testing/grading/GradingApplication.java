package nl.utwente.soa.digital.testing.grading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class GradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradingApplication.class, args);
	}

}
