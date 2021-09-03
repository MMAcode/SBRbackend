package makarov.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

// @EnableWebSecurity //to enable WEB security
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// public class SecurityConfiguration {


    @Autowired DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                // .withDefaultSchema()
                ;
    }

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     return new MyUserDetailsService_LoadUserByUsername();
    // }

    // @Autowired DataSource dataSource;
    // @Bean
    // public UserDetailsManager userDetailsManager(){
    //     return new JdbcUserDetailsManager(dataSource);
    // }


    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     // super.configure(http);
    //     http.authorizeRequests()
    //             //place most restrictive urls first; if /** first, then it will never go to the next steps
    //             .antMatchers("/admin").hasRole(Role.ADMIN.getValue())
    //             .antMatchers("/user").hasRole(Role.USER.getValue())
    //             .antMatchers("/public","public2").permitAll()
    //             .antMatchers("/**") //  /** to access all here and deeper
    //                 .hasRole(Role.ADMIN.getValue())
    //             .and()
    //                   .formLogin();
    // }


    @Bean
    // public PasswordEncoder getPasswordEncoder(){
    //     return new BCryptPasswordEncoder();
    /**
     * in controller, when receiving new user:
     * autoimport passwordEncoder
     * user.setPassword(passwordEncoder.encode(user.getPassword())
     * then createUser (userDetailsManager.createUser(user))
     */
    // }
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     // super.configure(auth);
    //     auth.inMemoryAuthentication()
    //             .withUser("u")
    //             .password("u") //overriding default user+password provided by Spring security (even when set in properties)
    //             .roles(Role.USER.getValue())
    //             .and() //chaining more users
    //             .withUser("a")
    //             .password("a") //overriding default user+password provided by Spring security (even when set in properties)
    //             .roles(Role.ADMIN.getValue())
    //              //.passwordEncoder(NoOpPasswordEncoder.getInstance())
    //             ;
    // }

    // @Autowired
    // DataSource dataSource; //by default pointing to H2
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.jdbcAuthentication()
    //             .dataSource(dataSource) //connect to specific database
    //             .withDefaultSchema()
    //             .withUser(User
    //                     .withUsername("u2")
    //             .password("u2")
    //             .roles(Role.USER.getValue()))
    //             .withUser(User
    //                     .withUsername("a2")
    //                     .password("a2")
    //                     .roles(Role.ADMIN.getValue())
    //             );
    // }




}
