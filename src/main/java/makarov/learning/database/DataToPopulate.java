package makarov.learning.database;

import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import makarov.learning.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("resetDb")
public class DataToPopulate {

    public DataToPopulate(UserRepository userRepository) {
    userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x1").username("x1").authorities(List.of(Authority.AUTH1)).build());
    userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x2").username("x2").authorities(List.of(Authority.AUTH2)).build());
    userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x3").username("x3").authorities(List.of(Authority.AUTH3)).build());
    userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x12").username("x12").authorities(List.of(Authority.AUTH1,Authority.AUTH2)).build());
    userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x123").username("x123").authorities(List.of(Authority.AUTH1,Authority.AUTH2,Authority.AUTH3)).build());
    }
}
