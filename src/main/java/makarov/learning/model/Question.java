package makarov.learning.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            optional = false)
    // @JsonBackReference
    @JsonIgnore
    private Quiz
            quiz;
    @Column(insertable = false, updatable = false) private Long
            quiz_id;



    @OneToMany(
            cascade = CascadeType.PERSIST, // to allow to persist nested entities in constructor
            fetch = FetchType.EAGER,
            orphanRemoval = true
            ,mappedBy = "question" //omitting this value causes connection table to drop
    )
    @JsonManagedReference
    private List<Choice>
            choices = new ArrayList<>();


    public void addChoices(Choice... q) {
        Arrays.stream(q).forEach(q1 -> {
                    q1.setQuestion(this);
                    choices.add(q1);
                }
        );
    }
}
