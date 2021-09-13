package makarov.learning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import makarov.learning.security.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
// @Table(name="quizs")
@NoArgsConstructor //needed for JPA?
@AllArgsConstructor // needed to use builder well
@Builder(toBuilder = true)
@ToString

// @Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long
            id;
    @NonNull
    private String
            title;

    @OneToMany(
            // targetEntity=Question.class,
            cascade = CascadeType.ALL, // to allow to persist nested entities in constructor
            // cascade = CascadeType.PERSIST, // to allow to persist nested entities in constructor
            fetch = FetchType.EAGER,
            // fetch = FetchType.LAZY,
            orphanRemoval = true
            ,mappedBy = "quiz" //omitting this value causes connection table to drop
    )
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();


    public Quiz associateQuestion(Question... q) {
        Arrays.stream(q).forEach(q1 -> {
                    q1.setQuiz(this);
                    questions.add(q1);
                }
        );

        return this;
    }

    public Quiz(@NonNull String title) {
        this.title = title;
    }


    public Question getQuestionBy(Long id){
        return this.getQuestions().stream().filter(q -> q.getId() == id).findAny().orElseThrow();
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
