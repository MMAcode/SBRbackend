package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.Quiz;
import makarov.learning.repository.QuestionRepository;
import makarov.learning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping({"","/","api/"})
@Slf4j
public class QuizController {
    @Autowired
    QuizRepository quizRepository;

    @GetMapping("quizzes")
    public Iterable<Quiz> getQuizzes(){
        log.info("quiz - logger");
        return quizRepository.findAll();
    }

    @GetMapping("quiz/{id}")
    public Optional<Quiz> getUser(@PathVariable Long id){
        return quizRepository.findById(id);
    }



}
