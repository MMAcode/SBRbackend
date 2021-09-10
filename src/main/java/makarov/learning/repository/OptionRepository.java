package makarov.learning.repository;

import makarov.learning.model.Choice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="")
public interface OptionRepository extends CrudRepository<Choice, Long> {
}
