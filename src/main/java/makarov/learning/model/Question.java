package makarov.learning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    // //to include property/entity (-> causing recursion)
    @ManyToOne(
            // fetch = FetchType.EAGER,
            fetch = FetchType.LAZY,
            // cascade = CascadeType.ALL
            cascade = CascadeType.PERSIST,
            // cascade = CascadeType.DETACH,
            optional = false
            // ,
            // targetEntity = Quiz.class,
    )
    // // @NonNull
    // @JoinColumn(
    //         name="quiz_id" //seems working fine with or without this whole annotation
    //
    //         // insertable = false, //false breaks the code
    //         // nullable=false,
    //         // updatable=false
    // )
    // @JsonIgnoreProperties({ "quiz" }) //not doing anything
    @JsonBackReference
    private Quiz quiz;
    @Column(insertable = false, updatable = false)
    private Long quiz_id;

    // @OneToMany(
    //         cascade = CascadeType.PERSIST, // to allow to persist nested entities in constructor
    //         fetch = FetchType.LAZY,
    //         orphanRemoval = true,
    //         mappedBy = "question" //omitting this value causes connection table to drop
    // )
    // @JsonManagedReference
    // private List<Option> options = new ArrayList<>();
}
