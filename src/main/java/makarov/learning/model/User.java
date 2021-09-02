package makarov.learning.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
// @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long
            id;
    @NonNull private String
            firstName;
    @Column(name="lastName") private String
            lastName; //if @Column not specified, defaults used
    @Column private String
            email;
    private String
            sex;

    public User(){}

    @Builder(toBuilder = true)
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    // if we want to implement builder with mandatory fields in its constructor:
    // public static UserBuilder builder(@NonNull String firstName){
    //     return new UserBuilder().firstName(firstName);
    // }
    //    		-->   userRepository.save(User.builder("MiroMandatoryName").build());
//


}
