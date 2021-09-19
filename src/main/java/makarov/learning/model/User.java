package makarov.learning.model;

import lombok.*;
import makarov.learning.security.Authority;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @NonNull @Column(columnDefinition="varchar(45)", unique = true) String
            username;
    @Column(columnDefinition="varchar(45)") String
            password;
    // @ElementCollection private List<String>
    //         authorities;
    // @ElementCollection
    @ElementCollection(fetch = FetchType.EAGER) // @LazyCollection(LazyCollectionOption.FALSE)
    // @CollectionTable(name = "userAuthorities", joinColumns = @JoinColumn(name = "username"))
    @CollectionTable(
            // name = "userAuthorities", //does not work
            // name = "authorities", //works
            joinColumns = @JoinColumn(name = "username"))

    // @CollectionTable(joinColumns = @JoinColumn(name = "username"))
    // @JoinColumn(name="username")
    @Column(name = "authority")
    //to match: /Users/mmakarov/.m2/repository/org/springframework/security/spring-security-core/5.5.1/spring-security-core-5.5.1.jar!/org/springframework/security/provisioning/JdbcUserDetailsManager.class
    private List<Authority>
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
