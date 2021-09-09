package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.Question;
import makarov.learning.model.Quiz;
import makarov.learning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping({"","/","api/"})
@Slf4j
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("questions")
    public Iterable<Question> getQuestions(){
        log.info("quiz - logger");
        return questionRepository.findAll();
    }

    @GetMapping("question/{id}")
    public Optional<Question> getUser(@PathVariable Long id){
        return questionRepository.findById(id);
    }



}
