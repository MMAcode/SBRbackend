package makarov.learning.repository;

import makarov.learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins="") // allow everywhere: https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-cors-global-java
// @RepositoryRestResource
// public interface UserRepository extends JpaRepository<User, Long> { - probably not for  mySql
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    Optional<User> findByUsername(String firstName);

    List<User> findByLastNameContains(String lastName);
    // List<User> findByFirstName(@Param("firstName") String firstName);
    // -> http://localhost:8080/rest/users/search/findByFirstName?firstName=MiroBuilder



//    custom query example
    // @Query("SELECT e FROM Expense e WHERE e.amount >= :amount")
    // public List<Expense> listItemsWithPriceOver(@Param("amount") float amount);

}
