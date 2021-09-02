package makarov.learning;

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

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// userRepository.save(User.builder().firstName("user").lastName("Makarov").username("x1").authorities(List.of(Authority.AUTH1)).build());
		// userRepository.save(User.builder().firstName("user").lastName("Makarov").username("x2").authorities(List.of(Authority.AUTH2)).build());
		// userRepository.save(User.builder().firstName("user").lastName("Makarov").username("x12").authorities(List.of(Authority.AUTH1,Authority.AUTH2)).build());
		// userRepository.save(User.builder().firstName("user").lastName("Makarov").username("x123").authorities(List.of(Authority.AUTH1,Authority.AUTH2,Authority.AUTH3)).build());
	}
}
