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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
// @Profile("resetDb")
public class DataToPopulate {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    @PostConstruct
    private void handleData(){
        if (!userRepository.findByUsername("x1").isPresent()) {populateDbWithData();}
        // updateData();
    }
    private void populateDbWithData() {
        log.info("Hi from DataToPopulate");
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x1").username("x1").authorities(List.of(Authority.AUTH1)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x2").username("x2").authorities(List.of(Authority.AUTH2)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x3").username("x3").authorities(List.of(Authority.AUTH3)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x12").username("x12").authorities(List.of(Authority.AUTH1, Authority.AUTH2)).build());
        // userRepository.save(User.builder().firstName("user").lastName("Makarov").password("x123").username("x123").authorities(List.of(Authority.AUTH1, Authority.AUTH2, Authority.AUTH3)).build());

        userRepository.save(User.builder().firstName("user").lastName("Makarov").password(passwordEncoder.encode("x1")).username("x1").authorities(List.of(Authority.user.getAuthority())).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password(passwordEncoder.encode("x2")).username("x2").authorities(List.of(Authority.manager.getAuthority())).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password(passwordEncoder.encode("x3")).username("x3").authorities(List.of(Authority.admin.getAuthority())).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password(passwordEncoder.encode("x12")).username("x12").authorities(List.of(Authority.user.getAuthority(), Authority.manager.getAuthority())).build());
        userRepository.save(User.builder().firstName("user").lastName("Makarov").password(passwordEncoder.encode("x123")).username("x123").authorities(List.of(Authority.user.getAuthority(), Authority.manager.getAuthority(), Authority.admin.getAuthority())).build());


        quizRepository.save(quizMathsWithFives());
        quizRepository.save(quizMathsWithTwentyFives());
        quizRepository.save(quizMathsWith(10));
        quizRepository.save(quizMathsWith(6));
        quizRepository.save(quizMathsWith(7));
    }


    private void updateData(){
        // // UPDATING:
        // // q2.setTitle("Question 2 Updated0");
        // // quizRepository.save(qz1);
        //
        // Quiz quiz = quizRepository.findById(1L).get();
        // // quizRepository.findById(1L).get();
        // log.info("quiz1 question 1 choice 1 id: {}",quiz.getQuestions().get(0).getChoices().get(0).getId());
        //
        // // quizRepository.findById(1L).get();
        // // quiz.setId(27);
        // // Question qToUpdate = quiz.getQuestions().stream().filter(q -> q.getId() == 1L).findAny().orElseThrow();
        //     System.out.println("question with id 1 found in quiz with id 1.");
        //     Question qToUpdate = quiz.getQuestionBy(1);

        // qToUpdate.setTitle("Question 2 Updated ");
        // qToUpdate.getChoiceBy(1L).setTitle("updatedChoice");
        // quiz.removeQuestionBy(2);

        // swapChoices12InQuestion1InQuiz(quiz);
        // quizRepository.save(quiz);

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

    private void swapChoices12InQuestion1InQuiz(Quiz quiz) {
        // Choice c1 = quiz.getQuestions().get(0).getChoices().get(0);
        List<Choice> lc = quiz.getQuestions().get(0).getChoices();
        Collections.swap(lc,0,1);
    }

    private Quiz quizMathsWithFives(){
        Question q11 = Question.builder().title("What is 5+5?").choices(new ArrayList<>()).build();
        Choice c111 = Choice.builder().title("5").build();
        Choice c112 = Choice.builder().title("10").correctAnswer(true).build();
        Choice c113 = Choice.builder().title("15").build();
        Choice c114 = Choice.builder().title("20").build();
        q11.associateExternalChoices(c111, c112, c113,c114);

        Question q12 = Question.builder().title("What is 5*5?").choices(new ArrayList<>()).build();
        Choice c121 = Choice.builder().title("5").build();
        Choice c122 = Choice.builder().title("25").correctAnswer(true).build();
        Choice c123 = Choice.builder().title("15").build();
        Choice c124 = Choice.builder().title("20").build();
        q12.associateExternalChoices(c121, c122, c123, c124);

        Question q13 = Question.builder().title("What is 5*5-5?").choices(new ArrayList<>()).build();
        Choice c131 = Choice.builder().title("5").build();
        Choice c132 = Choice.builder().title("25").build();
        Choice c133 = Choice.builder().title("15").build();
        Choice c134 = Choice.builder().title("20").correctAnswer(true).build();
        q13.associateExternalChoices(c131, c132, c133, c134);

        Question q14 = Question.builder().title("What is 5*(5-5)/5?").choices(new ArrayList<>()).build();
        Choice c141 = Choice.builder().title("5").build();
        Choice c142 = Choice.builder().title("25").build();
        Choice c143 = Choice.builder().title("0").correctAnswer(true).build();
        Choice c144 = Choice.builder().title("20").build();
        q14.associateExternalChoices(c141, c142, c143, c144);

        return new Quiz("Maths with 5").associateQuestion(q11, q12,q13,q14).addQuestionsPositionsIfNeeded();
    }

    private Quiz quizMathsWithTwentyFives(){
        Question q11 = Question.builder().title("What is 25+255?").choices(new ArrayList<>()).build();
        Choice c111 = Choice.builder().title("280").correctAnswer(true).build();
        Choice c112 = Choice.builder().title("275").build();
        Choice c113 = Choice.builder().title("255").build();
        Choice c114 = Choice.builder().title("205").build();
        q11.associateExternalChoices(c111, c112, c113,c114);

        Question q12 = Question.builder().title("What is 25*5?").choices(new ArrayList<>()).build();
        Choice c121 = Choice.builder().title("25").build();
        Choice c122 = Choice.builder().title("125").correctAnswer(true).build();
        Choice c123 = Choice.builder().title("155").build();
        Choice c124 = Choice.builder().title("255").build();
        q12.associateExternalChoices(c121, c122, c123, c124);

        Question q13 = Question.builder().title("What is 5*25-25?").choices(new ArrayList<>()).build();
        Choice c131 = Choice.builder().title("500").build();
        Choice c132 = Choice.builder().title("250").build();
        Choice c133 = Choice.builder().title("150").build();
        Choice c134 = Choice.builder().title("100").correctAnswer(true).build();
        q13.associateExternalChoices(c131, c132, c133, c134);

        Question q14 = Question.builder().title("What is 5*(25-5)/5?").choices(new ArrayList<>()).build();
        Choice c141 = Choice.builder().title("5").build();
        Choice c142 = Choice.builder().title("25").build();
        Choice c143 = Choice.builder().title("0").build();
        Choice c144 = Choice.builder().title("20").correctAnswer(true).build();
        q14.associateExternalChoices(c141, c142, c143, c144);

        return new Quiz("Maths with 25").associateQuestion(q11, q12,q13,q14).addQuestionsPositionsIfNeeded();
    }

    private Quiz quizMathsWith(int i){
        Question q11 = Question.builder().title("What is "+i+"+"+i+"?").choices(new ArrayList<>()).build();
        Choice c111 = Choice.builder().title(String.valueOf(i+i)).correctAnswer(true).build();
        Choice c112 = Choice.builder().title(String.valueOf(i*i)).build();
        Choice c113 = Choice.builder().title(String.valueOf(i*i-i)).build();
        Choice c114 = Choice.builder().title(String.valueOf(i*i-1)).build();
        q11.associateExternalChoices(c111, c112, c113,c114);

        Question q12 = Question.builder().title("What is "+i+"*"+i+"?").choices(new ArrayList<>()).build();
        Choice c121 = Choice.builder().title(String.valueOf(i+i)).build();
        Choice c122 = Choice.builder().title(String.valueOf(i*i)).correctAnswer(true).build();
        Choice c123 = Choice.builder().title(String.valueOf(i*i-i)).build();
        Choice c124 = Choice.builder().title(String.valueOf(i*i-1)).build();
        q12.associateExternalChoices(c121, c122, c123, c124);

        Question q13 = Question.builder().title("What is "+i+"*"+i+"-"+i+"?").choices(new ArrayList<>()).build();
        Choice c131 = Choice.builder().title(String.valueOf(i+i)).build();
        Choice c132 = Choice.builder().title(String.valueOf(i*i)).build();
        Choice c133 = Choice.builder().title(String.valueOf(i*i-i)).correctAnswer(true).build();
        Choice c134 = Choice.builder().title(String.valueOf(i*i-1)).build();
        q13.associateExternalChoices(c131, c132, c133, c134);

        Question q14 = Question.builder().title("What is"+i+"*"+i+"-"+i+"/"+i+"?").choices(new ArrayList<>()).build();
        Choice c141 = Choice.builder().title(String.valueOf(i+i)).build();
        Choice c142 = Choice.builder().title(String.valueOf(i*i)).build();
        Choice c143 = Choice.builder().title(String.valueOf(i*i-i)).build();
        Choice c144 = Choice.builder().title(String.valueOf(i*i-1)).correctAnswer(true).build();
        q14.associateExternalChoices(c141, c142, c143, c144);

        return new Quiz("Maths with "+i).associateQuestion(q11, q12,q13,q14).addQuestionsPositionsIfNeeded();
    }
}
