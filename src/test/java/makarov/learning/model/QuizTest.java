package makarov.learning.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class QuizTest {
    Question question;
    Question question2;
    Quiz quiz;
    Quiz quiz2;
    Choice choice;

    @BeforeEach
    void setUp(){
        choice = Choice.builder().title("ch1").build();
        // Choice c2 = Choice.builder().title("ch2").correctAnswer(true).build();

        question = Question.builder().title("question1").choices(new ArrayList<>()).build();
        question2 = Question.builder().title("question2").choices(new ArrayList<>()).build();
        // question.associateExternalChoices(c1, c2);

        quiz = new Quiz("q1");
        quiz2 = new Quiz("q2");
        // q1.associateQuestion(qu1);
    }

    @Test
    void associateQuestion() {
        quiz.associateQuestion(question);
        assertThat(question.getQuiz()).isEqualTo(quiz);
        assertThat(quiz.getQuestions()).contains(question);
    }

    @Test
    void associateAllExistingQuestionsAndChoices() {
        question.getChoices().add(choice);
        quiz.getQuestions().add(question);
        quiz.associateAllExistingQuestionsAndChoices();

        assertThat(question.getQuiz()).isEqualTo(quiz);
        assertThat(choice.getQuestion()).isEqualTo(question);
    }

    @Test
    void addQuestionsPositionsIfNeeded() {
        quiz.associateQuestion(question, question2);
        assertThat(question.getPositionFrom0()).isEqualTo(-1);
        assertThat(question2.getPositionFrom0()).isEqualTo(-1);

        quiz.addQuestionsPositionsIfNeeded();
        assertThat(question.getPositionFrom0()).isEqualTo(0);
        assertThat(question2.getPositionFrom0()).isEqualTo(1);

    }
}