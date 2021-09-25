package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.Quiz;
import makarov.learning.model.QuizProjection_NameId;
import makarov.learning.model.QuizProjection_NoAns;
import makarov.learning.model.QuizProjection_NoChoices;
import makarov.learning.repository.QuizRepository;
import makarov.learning.security.Authority;
import makarov.learning.service.QuizUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"","/","api/"})
@Slf4j
public class QuizController {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizUpdater quizUpdater;

    @GetMapping("quizzes")
    // public Iterable<Quiz> getQuizzes(Authentication authentication){
    public Iterable getQuizzes(Authentication authentication){
        // if (authentication.getAuthorities().contains(Authority.admin)){ return quizRepository.findAll(); }
        // else if (authentication.getAuthorities().contains(Authority.manager)){ return quizRepository.findAll(); }
        // else { return quizRepository.findAll_WithoutAnswers(); }
        if (authentication.getAuthorities().contains(Authority.user)){ return quizRepository.findAll_WithoutAnswers(); }
        else { return quizRepository.findAll(); }
    }

    @GetMapping("quiz/{id}")
    public Optional<Quiz> getQuiz(@PathVariable Long id){
        return quizRepository.findById(id);
    }

    @PostMapping("quiz/update")
    // @ResponseBody // tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
    public void updateQuiz(@RequestBody Quiz quiz){
        System.out.println(quiz);
        quiz.associateAllExistingQuestionsAndChoices();
        quiz.addQuestionsPositionsIfNeeded();
        quizRepository.save(quiz);
    }
    ////FILTERED ENTITIES:
    // @GetMapping("qf")
    // public Collection<QuizProjection_NameId> findFiltered(){
    //     return quizRepository.findBy();
    // }

    @GetMapping("qf/{id}")
    public Optional<QuizProjection_NoAns> getCleanedQuiz (@PathVariable Long id){
        return quizRepository.getQuizById(id);
    }

    @GetMapping("qfNoChoices/{id}")
    public Optional<QuizProjection_NoChoices> getCleanedQuiz2 (@PathVariable Long id){
        return quizRepository.getQuizWithoutQuestionOptionsById(id);
    }

}
