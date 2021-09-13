package makarov.learning;

import makarov.learning.helpers.HelperMethods;
import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import makarov.learning.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AppStarter implements CommandLineRunner {

	@Autowired private UserRepository userRepository;
	@Autowired HelperMethods helperMethods;

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// helperMethods.printSpringContextBeans();
	}
}
