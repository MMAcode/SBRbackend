package makarov.learning.repository;

import makarov.learning.model.Question;
import makarov.learning.model.Quiz;
import makarov.learning.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins="")
public interface QuizRepository extends CrudRepository<Quiz, Long> {

    @Modifying
    @Query("update Quiz q set q.title = ?1 where q.id = ?2")
    int update(String title, String quizId);
}
