package makarov.learning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStarter implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
