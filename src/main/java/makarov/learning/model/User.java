package makarov.learning.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
// @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column @NonNull private String firstName;
    @Column(name="lastName") private String lastName; //if column name not specified, property name is used by default
    @Column private String email;

    // @Builder
    public User(){}

    @Builder(toBuilder = true)
    public User(@NonNull String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    // @Builder
    // public void buildHelp(){};


}
