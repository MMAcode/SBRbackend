package makarov.learning.database;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.Choice;
import makarov.learning.model.Question;
import makarov.learning.model.Quiz;
import makarov.learning.model.User;
import makarov.learning.repository.QuestionRepository;
import makarov.learning.repository.QuizRepository;
import makarov.learning.repository.UserRepository;
import makarov.learning.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
// @Profile("resetDb")
public class DataToPopulate {

    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    @PostConstruct
    private void populateDbWithData() {
        log.info("Hi from DataToPopulate");
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x1").username("x1").authorities(List.of(Authority.AUTH1)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x2").username("x2").authorities(List.of(Authority.AUTH2)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x3").username("x3").authorities(List.of(Authority.AUTH3)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x12").username("x12").authorities(List.of(Authority.AUTH1, Authority.AUTH2)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x123").username("x123").authorities(List.of(Authority.AUTH1, Authority.AUTH2, Authority.AUTH3)).build());

        //new
        Choice c1 = Choice.builder().title("ch1").build();
        Choice c2 = Choice.builder().title("ch2").correctAnswer(true).build();
        Choice c3 = Choice.builder().title("ch3").build();
        Choice c4 = Choice.builder().title("ch4").build();


        // also works
        Question q1 = Question.builder().title("question1").choices(new ArrayList<>()).build();
        q1.addChoices(c1, c2, c3);
        Question q2 = Question.builder().title("question2").choices(new ArrayList<>()).build();
        q2.addChoices(c4);
        Question q3 = Question.builder().title("question3").build();
        Question q4 = Question.builder().title("question4").build();
        Question q5 = Question.builder().title("question5").build();
        // Quiz qz1 = Quiz.builder().title("quiz 1").build();
        Quiz qz1 = new Quiz("quiz1").associateQuestion(q1, q2);
        Quiz qz2 = new Quiz("quiz2").associateQuestion(q3, q4);
        // quizRepository.save(qz1);
        // quizRepository.save(qz2);

        //UPDATING:
        // q2.setTitle("Question 2 Updated0");
        // quizRepository.save(qz1);

        Quiz quiz = quizRepository.findById(1L).get();
        // quiz.setId(27);
        // Question qToUpdate = quiz.getQuestions().stream().filter(q -> q.getId() == 1L).findAny().orElseThrow();
            System.out.println("question with id 1 found in quiz with id 1.");
            Question qToUpdate = quiz.getQuestionBy(1L);
        qToUpdate.setTitle("Question 2 Updated ");
        qToUpdate.getChoiceBy(1L).setTitle("updatedChoice");
        // qToUpdate.

        log.info("before saving quiz");
        quizRepository.save(quiz);

        // // quiz.getQuestions().
        // System.out.println();
        // // questionRepository
        // // quizRepository.








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

    }
}
