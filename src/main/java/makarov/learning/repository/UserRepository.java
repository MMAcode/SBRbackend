package makarov.learning.repository;

import makarov.learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins="")
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(@Param("firstName") String firstName);
    // -> http://localhost:8080/rest/users/search/findByFirstName?firstName=MiroBuilder
}
