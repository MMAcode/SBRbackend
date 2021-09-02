package makarov.learning.repository;

import makarov.learning.model.MMUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins="")
// @RepositoryRestResource
// public interface UserRepository extends JpaRepository<User, Long> { - probably not for  mySql
public interface UserRepository extends CrudRepository<MMUser, Long> {
    List<MMUser> findByFirstName(String firstName);
    Optional<MMUser> findByUsername(String firstName);

    List<MMUser> findByLastNameContains(String lastName);
    // List<User> findByFirstName(@Param("firstName") String firstName);
    // -> http://localhost:8080/rest/users/search/findByFirstName?firstName=MiroBuilder



//    custom query example
    // @Query("SELECT e FROM Expense e WHERE e.amount >= :amount")
    // public List<Expense> listItemsWithPriceOver(@Param("amount") float amount);

}
