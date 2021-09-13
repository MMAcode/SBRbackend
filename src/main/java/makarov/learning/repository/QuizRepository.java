package makarov.learning.repository;

import makarov.learning.model.Quiz;
import makarov.learning.model.QuizProjection_NameId;
import makarov.learning.model.QuizProjection_NoAns;
import makarov.learning.model.QuizProjection_NoChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;

@Repository
@CrossOrigin(origins="")
// public interface QuizRepository extends CrudRepository<Quiz, Long> {
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    //UPDATING QUIZ
    @Modifying //to enhance the @Query annotation to execute not only SELECT queries but also INSERT, UPDATE, DELETE, and even DDL queries.
    @Query("update Quiz q set q.title = ?1 where q.id = ?2")
    int updateQuizTitleMM(String title, String quizId);




    //FILTERED ENTITIES:
    Collection<QuizProjection_NameId> findBy();
    // // Optional<QuizProjection_NoAns> findById(Long id);


    // // @Query("select q from Quiz where q.id =?1")
    Optional<QuizProjection_NoAns> getQuizById(Long id);

    @Query("select q from Quiz q where q.id =?1")
    Optional<QuizProjection_NoChoices> getQuizWithoutQuestionOptionsById(Long id);

}
