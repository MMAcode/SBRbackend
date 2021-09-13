package makarov.learning.model;

import java.util.List;

public interface QuizProjection_NoAns {
    String getId();
    String getTitle();
    List<QuestionProjection_NoAns> getQuestions();

    public interface QuestionProjection_NoAns {
        String getId();
        String getTitle();
        String getQuiz_id();
        List<ChoiceProjection_NoAns> getChoices();

        public interface ChoiceProjection_NoAns {
            String getId();
            String getTitle();
            // String getQuestion_id();
        }
    }
}
