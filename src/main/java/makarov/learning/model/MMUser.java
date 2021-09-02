package makarov.learning.model;

import lombok.*;
import makarov.learning.security.Authority;

import javax.persistence.*;
import java.util.List;

// @Data
@Getter
@Setter
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString

// @Builder
public class MMUser {
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
    @NonNull @Column(columnDefinition="varchar(45)", unique = true) String
            username;
    @Column(columnDefinition="varchar(45)") String
            password;
    // @ElementCollection private List<String>
    //         authorities;
    @ElementCollection private List<Authority>
            authorities;


    // public User(){}

    // @Builder(toBuilder = true)
    // public User(String firstName, String lastName, String email) {
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.email = email;
    // }

    // if we want to implement builder with mandatory fields in its constructor:
    // public static UserBuilder builder(@NonNull String firstName){
    //     return new UserBuilder().firstName(firstName);
    // }
    //    		-->   userRepository.save(User.builder("MiroMandatoryName").build());
//


}
