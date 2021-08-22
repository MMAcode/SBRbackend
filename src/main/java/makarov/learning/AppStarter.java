package makarov.learning;

import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStarter implements CommandLineRunner {

	@Autowired private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Miro1"));
		userRepository.save(new User("Miro2"));
		userRepository.save(new User("Miro3"));
	}
}
