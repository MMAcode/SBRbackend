package makarov.learning.repository;

import makarov.learning.model.Question;
import makarov.learning.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="")
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
