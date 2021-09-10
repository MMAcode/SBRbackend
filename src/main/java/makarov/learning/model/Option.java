package makarov.learning.model;
//
// import com.fasterxml.jackson.annotation.JsonBackReference;
// import lombok.*;
//
// import javax.persistence.*;
// import java.util.List;
//
// // @Data
// @Getter
// @Setter
// @Entity
// // @Table(name="questions")
// @NoArgsConstructor //needed for JPA?
// @AllArgsConstructor // needed to use builder well
// @Builder(toBuilder = true)
// @ToString
//
// // @Builder
// public class Option {
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long
//             id;
//     @NonNull private String
//             title;
//     @ManyToOne(
//             fetch = FetchType.LAZY,
//             cascade = CascadeType.PERSIST,
//             // cascade = CascadeType.DETACH,
//             optional = false
//     )
//     @JsonBackReference
//     private Question question;
//
//
//
//
//
//     // @ElementCollection(fetch = FetchType.LAZY) // @LazyCollection(LazyCollectionOption.FALSE)
//     // @CollectionTable(name = "quizQuestions")
//     // // @CollectionTable(joinColumns = @JoinColumn(name = "username"))
//     // @JoinColumn(name="id")
//     // @Column(name = "question")
//     // //to match: /Users/mmakarov/.m2/repository/org/springframework/security/spring-security-core/5.5.1/spring-security-core-5.5.1.jar!/org/springframework/security/provisioning/JdbcUserDetailsManager.class
//     // private List<Question>
//     //         questions;
//
// }
