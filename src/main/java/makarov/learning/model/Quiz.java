package makarov.learning.model;

import lombok.*;
import makarov.learning.security.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Data
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long
            id;
    @NonNull private String
            title;


    // @OneToMany()
    @OneToMany(
            // targetEntity=Question.class,
            // cascade = CascadeType.ALL,
            // fetch = FetchType.EAGER,
            // orphanRemoval = true,
            // mappedBy = "quizs"
    )
    // private List<Question> questions = new ArrayList<>();
    private List<Question> questions;





    // @ElementCollection(fetch = FetchType.LAZY) // @LazyCollection(LazyCollectionOption.FALSE)
    // @CollectionTable(name = "quizQuestions")
    // // @CollectionTable(joinColumns = @JoinColumn(name = "username"))
    // @JoinColumn(name="id")
    // @Column(name = "question")
    // //to match: /Users/mmakarov/.m2/repository/org/springframework/security/spring-security-core/5.5.1/spring-security-core-5.5.1.jar!/org/springframework/security/provisioning/JdbcUserDetailsManager.class
    // private List<Question>
    //         questions;

}
