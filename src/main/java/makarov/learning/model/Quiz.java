package makarov.learning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.*;
import makarov.learning.security.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor //needed for JPA?
@AllArgsConstructor // needed to use builder well
@Builder(toBuilder = true)
@ToString
public class Quiz {// @Table(name="quizs")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long
            id;
    @NonNull
    private String
            title;

    @OneToMany(
            cascade = CascadeType.ALL, // to allow to persist nested entities in constructor
            orphanRemoval = true
            ,mappedBy = "quiz" //omitting this value causes connection table to drop
    )
    private List<Question> questions = new ArrayList<>();

    public Quiz associateQuestion(Question... q) {
        Arrays.stream(q).forEach(q1 -> {
                    q1.setQuiz(this);
                    questions.add(q1);
                }
        );

        return this;
    }
    public Quiz associateAllExistingQuestionsAndChoices(){
        this.questions.forEach(question-> {
            question.setQuiz(this);
            question.associateAllExistingChoices();
        });
        return this;
    }

    public Quiz addQuestionsPositionsIfNeeded(){
        // this.getQuestions().forEach((q,p)->{});
        int positionFrom0 = 0;
        for (Question q :this.getQuestions()){
            if (q.getPositionFrom0()<0) {
                q.setPositionFrom0(positionFrom0++);
                  System.out.println();
            }
        }
        return this;
    }

    public Quiz(@NonNull String title) {
        this.title = title;
    }

    public Question getQuestionBy(long id){
        return this.getQuestions().stream().filter(q -> q.getId() == id).findAny().orElseThrow(()-> new RuntimeException("question with id "+id+" not found in quiz with id " + this.id));
    }

    public void removeQuestionBy(long id){
        questions.remove(getQuestionBy(id));
    }

    // @ElementCollection(fetch = FetchType.LAZY) // @LazyCollection(LazyCollectionOption.FALSE)
    // @CollectionTable(name = "quizQuestions")
    // // @CollectionTable(joinColumns = @JoinColumn(name = "username"))
    // @JoinColumn(name="id")
    // @Column(name = "question")
    // //to match: /Users/mmakarov/.m2/repository/org/springframework/security/spring-security-core/5.5.1/spring-security-core-5.5.1.jar!/org/springframework/security/provisioning/JdbcUserDetailsManager.class
    // private List<Question>
    //         questions;

}
