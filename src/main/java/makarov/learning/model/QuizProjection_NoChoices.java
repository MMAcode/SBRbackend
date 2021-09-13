package makarov.learning.model;

import java.util.List;

public interface QuizProjection_NoChoices {
    String getId();
    String getTitle();
    List<QuestionProjection_NoChoices> getQuestions();

    public interface QuestionProjection_NoChoices {
        String getId();
        String getTitle();
        // String getQuiz_id();
    }
}
