package makarov.learning.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long
        id;
    @NonNull
    private String
        title;
    @NonNull
    // @Column(unique = true) //as column contains questions from multiple quizzes
    @Builder.Default
    private int
        positionFrom0 = -1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Quiz
        quiz;
    @OneToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        orphanRemoval = true
        , mappedBy = "question" //omitting this value causes connection table to drop
    ) private List<Choice>
        choices = new ArrayList<>();

    public void associateExternalChoices(Choice... choices) {
        Arrays.stream(choices).forEach(choice -> {
                choice.setQuestion(this);
                this.choices.add(choice);
            }
        );
    }

    public void associateAllExistingChoices() {
        this.choices.forEach(choice -> {
            choice.setQuestion(this);
        });
    }


    public Choice getChoiceBy(Long id) {
        return this.getChoices().stream().filter(c -> c.getId() == id).findAny().orElseThrow(() -> new RuntimeException("choice with id " + id + " not found in question with id " + this.id));
    }

    // public void removeChoiceBy(long id) {
    //     choices.remove(getChoiceBy(id));
    // }
}
