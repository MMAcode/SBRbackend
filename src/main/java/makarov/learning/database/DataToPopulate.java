package makarov.learning.database;

import makarov.learning.model.Question;
import makarov.learning.model.Quiz;
import makarov.learning.model.User;
import makarov.learning.repository.QuestionRepository;
import makarov.learning.repository.QuizRepository;
import makarov.learning.repository.UserRepository;
import makarov.learning.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
// @Profile("resetDb")
public class DataToPopulate {

    @Autowired UserRepository userRepository;
    @Autowired QuizRepository quizRepository;
    @Autowired QuestionRepository questionRepository;

    @PostConstruct
    private void populateDbWithData(){
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x1").username("x1").authorities(List.of(Authority.AUTH1)).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x2").username("x2").authorities(List.of(Authority.AUTH2)).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x3").username("x3").authorities(List.of(Authority.AUTH3)).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x12").username("x12").authorities(List.of(Authority.AUTH1, Authority.AUTH2)).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x123").username("x123").authorities(List.of(Authority.AUTH1, Authority.AUTH2, Authority.AUTH3)).build());


    //     //tested, works
    //     // Quiz qz1 = Quiz.builder().title("quiz 1").questions(new ArrayList<>()).build();
    //     // Quiz qz2 = Quiz.builder().title("quiz 1").questions(new ArrayList<>()).build();
    //     // Question q1 = Question.builder().title("q1").quiz(qz1).build();
    //     // Question q2 = Question.builder().title("q2").quiz(qz1).build();
    //     // Question q3 = Question.builder().title("q2").quiz(qz2).build();
    //     // Question q4 = Question.builder().title("q2").quiz(qz2).build();
    //     // Question q5 = Question.builder().title("q2").quiz(qz2).build();
    //     // qz1.getQuestions().add(q1);
    //     // qz1.getQuestions().add(q2);
    //     // qz2.getQuestions().add(q3);
    //     // qz2.getQuestions().add(q4);
    //     // // questionRepository.save(q1);
    //     //
    //     //
    //     // quizRepository.save(qz1);
    //     // quizRepository.save(qz2);
    //
    //
    //
    //     // //also works
    //     // Question q1 = Question.builder().title("q1").build();
    //     // Question q2 = Question.builder().title("q2").build();
    //     // Question q3 = Question.builder().title("q3").build();
    //     // Question q4 = Question.builder().title("q4").build();
    //     // Question q5 = Question.builder().title("q5").build();
    //     // // Quiz qz1 = Quiz.builder().title("quiz 1").build();
    //     // Quiz qz1 = new Quiz("q1");
    //     //         qz1.associateQuestion(q1,q2);
    //     // Quiz qz2 = new Quiz("q2");
    //     //         qz2.associateQuestion(q3,q4);
    //     // quizRepository.save(qz1);
    //     // quizRepository.save(qz2);
    //
        // also works
        Question q1 = Question.builder().title("q1").build();
        Question q2 = Question.builder().title("q2").build();
        Question q3 = Question.builder().title("q3").build();
        Question q4 = Question.builder().title("q4").build();
        Question q5 = Question.builder().title("q5").build();
        // Quiz qz1 = Quiz.builder().title("quiz 1").build();
        Quiz qz1 = new Quiz("q1").associateQuestion(q1,q2);
        Quiz qz2 = new Quiz("q2").associateQuestion(q3,q4);
        quizRepository.save(qz1);
        quizRepository.save(qz2);
    }
}
