package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.Quiz;
import makarov.learning.model.QuizProjection_NameId;
import makarov.learning.model.QuizProjection_NoAns;
import makarov.learning.repository.QuizRepository;
import makarov.learning.service.QuizUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping({"","/","api/"})
@Slf4j
public class QuizController {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizUpdater quizUpdater;

    @GetMapping("quizzes")
    public Iterable<Quiz> getQuizzes(){
        log.info("quiz - logger");
        return quizRepository.findAll();
    }

    @GetMapping("quiz/{id}")
    public Optional<Quiz> getQuiz(@PathVariable Long id){
        return quizRepository.findById(id);
    }

    //FILTERED ENTITIES:
    @GetMapping("qf")
    public Collection<QuizProjection_NameId> findFiltered(){
        return quizRepository.findBy();
    }

    @GetMapping("qf/{id}")
    public Optional<QuizProjection_NoAns> getCleanedQuiz (@PathVariable Long id){
        return quizRepository.getQuizById(id);
    }

}
