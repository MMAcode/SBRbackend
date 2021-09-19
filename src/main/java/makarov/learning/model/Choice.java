package makarov.learning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor //needed for JPA?
@AllArgsConstructor // needed to use builder well
@Builder(toBuilder = true)
public class Choice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long
            id;
    @NonNull private String
            title;
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            optional = false)
    @JsonIgnore
    private Question
        question;
    boolean correctAnswer = false;
}
