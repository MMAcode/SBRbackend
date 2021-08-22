package makarov.learning.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
// @Data
@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NonNull private String firstName;
    @Column(name="lastName") private String lastName; //if column name not specified, property name is used by default
    @Column private String email;

    public UserModel(){}

    public UserModel(@NonNull String firstName) {
        super();
        this.firstName = firstName;
    }
}
