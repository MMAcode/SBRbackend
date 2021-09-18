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
            optional = true)
    // @JsonBackReference
    // @JsonIgnoreProperties({"quiz_id","quiz","quizzes"})
    @JsonIgnore
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    private Quiz
            quiz;
    // @Column(insertable = false, updatable = false) private Long
    //         quiz_id;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
            ,mappedBy = "question" //omitting this value causes connection table to drop
    )
    // @JsonManagedReference
    private List<Choice>
            choices = new ArrayList<>();


    public void addChoices(Choice... q) {
        Arrays.stream(q).forEach(q1 -> {
                    q1.setQuestion(this);
                    choices.add(q1);
                }
        );
    }

    public Choice getChoiceBy(Long id){
        return this.getChoices().stream().filter(c -> c.getId() == id).findAny().orElseThrow(()->new RuntimeException("choice with id "+id+" not found in question with id " + this.id));
    }

    public void removeChoiceBy(long id){
        choices.remove(getChoiceBy(id));
    }
}
