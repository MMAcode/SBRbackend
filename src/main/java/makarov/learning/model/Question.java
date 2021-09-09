package makarov.learning.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// @Data
@Getter
@Setter
@Entity
// @Table(name="questions")
@NoArgsConstructor //needed for JPA?
@AllArgsConstructor // needed to use builder well
@Builder(toBuilder = true)
@ToString

// @Builder
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long
            id;
    @NonNull private String
            title;

    //to include property/entity (-> causing recursion)
    @ManyToOne(
            // fetch = FetchType.EAGER,
            fetch = FetchType.LAZY,
            // cascade = CascadeType.ALL
            cascade = CascadeType.PERSIST,
            optional = false
            // ,
            // targetEntity = Quiz.class,
    )
    // @NonNull
    @JoinColumn(
            // name="CUST_ID",
            // insertable = false, //false breaks the code
            nullable=false,
            updatable=false
    )
    private Quiz quiz;





    // @ElementCollection(fetch = FetchType.LAZY) // @LazyCollection(LazyCollectionOption.FALSE)
    // @CollectionTable(name = "quizQuestions")
    // // @CollectionTable(joinColumns = @JoinColumn(name = "username"))
    // @JoinColumn(name="id")
    // @Column(name = "question")
    // //to match: /Users/mmakarov/.m2/repository/org/springframework/security/spring-security-core/5.5.1/spring-security-core-5.5.1.jar!/org/springframework/security/provisioning/JdbcUserDetailsManager.class
    // private List<Question>
    //         questions;

}
