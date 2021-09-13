package makarov.learning.service;

import makarov.learning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizUpdater {
    @Autowired
    QuizRepository quizRepository;

    public int updateQuizTitle(String id, String title){

        return quizRepository.updateQuizTitleMM("title updated","1");
    }
}
