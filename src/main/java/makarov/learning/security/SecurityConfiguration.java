package makarov.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// @EnableWebSecurity //to enable WEB security
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// public class SecurityConfiguration {


    public UrlBasedCorsConfigurationSource corsUrlSetupMiro() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // // config.setAllowedHeaders("")
        // config.setAllowCredentials(true);
        // config.addAllowedOrigin("*");
        // config.addAllowedHeader("*");
        // config.addAllowedMethod("OPTIONS");
        // config.addAllowedMethod("GET");
        // config.addAllowedMethod("POST");
        // config.addAllowedMethod("PUT");
        // config.addAllowedMethod("DELETE");
        // source.registerCorsConfiguration("/**", config);



        // config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOrigins(Collections.singletonList("*"));
        // config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);

        return source;
    }



    // @Bean
    // public CorsFilter corsFilter() {
    //     final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     final CorsConfiguration config = new CorsConfiguration();
    //     // config.setAllowCredentials(true);
    //     // Don't do this in production, use a proper list  of allowed origins
    //     config.setAllowedOrigins(Collections.singletonList("*"));
    //     // config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
    //     config.setAllowedHeaders(Collections.singletonList("*"));
    //     config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
    //     source.registerCorsConfiguration("/**", config);
    //     return new CorsFilter(source);
    // }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        // config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOrigins(Collections.singletonList("*"));
        // config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Override //from: https://github.com/in28minutes/spring-boot-react-fullstack-examples/blob/master/spring-boot-react-basic-auth-login-logout/backend-spring-boot-react-basic-auth-login-logout/src/main/java/com/in28minutes/fullstack/springboot/fullstack/basic/authentication/springbootfullstackbasicauthloginlogout/basic/auth/SpringSecurityConfigurationBasicAuth.java
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TODO: for production, must be reconfigured in order to disable only in specific cases. This line was added because without it, HTTP POST requests did not work.
                .authorizeRequests()
                // .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and().cors() //uncomment to pick up corsFilter bean
                // .configurationSource(corsUrlSetupMiro()) instead of Cors Bean
                .and()
                // .httpBasic()
                    .formLogin().defaultSuccessUrl("/users")
                .and().rememberMe().key("uniqueAndSecretMiro")
                ;
    }

    // @Autowired
    // DataSource dataSource; //by default pointing to H2
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.jdbcAuthentication().dataSource(dataSource); //connect to specific database
        auth.userDetailsService(userDetailsService())
               ;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService_LoadUserByUsername();
    }

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
