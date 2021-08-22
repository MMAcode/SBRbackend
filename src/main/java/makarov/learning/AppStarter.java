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
		userRepository.save(User.builder().firstName("MiroBuilder").build());
		// userRepository.save(User.builder().lastName("Makarov").build()); //Does not compile as first name is required
		userRepository.save(User.builder().firstName("MiroBuilder2").lastName("Makarov").build());
		userRepository.save(User.builder().firstName("MiroBuilder2").email("someEmail").build());
	}
}
