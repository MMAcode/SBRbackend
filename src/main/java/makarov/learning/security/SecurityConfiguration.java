package makarov.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity //to enable WEB security
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000/", "localhost:3000/")); //to allow receive cookies
        config.setAllowCredentials(true); //to allow receive cookies
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .anonymous()
            .and()// to create anonymous user
            .csrf().disable() //TODO: for production, must be reconfigured in order to disable only in specific cases. This line was added because without it, HTTP POST requests did not work.
            .authorizeRequests() //to enable antMatchers
            .antMatchers(HttpMethod.GET, "/login/**", "/logout", "/error", "loginFailed").permitAll() //not working
            .antMatchers(HttpMethod.POST).hasAuthority("admin") //hasAuthority/role -> XY (not ROLE_XY)
            .antMatchers(HttpMethod.DELETE).hasAuthority(Authority.admin.getAuthority())
            .antMatchers(HttpMethod.GET, "/quizzes").hasAnyAuthority(Authority.user.getAuthority(), Authority.manager.getAuthority(), Authority.admin.getAuthority())

            .and().cors() //uncomment to pick up corsFilter bean

            .and().formLogin().loginProcessingUrl("/login")
            .defaultSuccessUrl("/userInfo")
            .loginPage("/loginFailed")
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(getPasswordEncoder())
        ;
    }

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(); }
}
