package makarov.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
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

    // ////REMEMBER ME:
    // //https://stackoverflow.com/questions/29563784/issue-with-spring-security-remember-me-token-not-being-set-on-securitycontexthol
    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setRememberMeServices(rememberMeServices());
        return filter;
    }

    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
        return new RememberMeAuthenticationFilter(authenticationManager(), rememberMeServices());
    }

    private static String rememberMeKey = "uniqueAndSecretMiro";

    @Bean
    public RememberMeServices rememberMeServices() throws Exception {
        TokenBasedRememberMeServices rms = new TokenBasedRememberMeServices(rememberMeKey, userDetailsService());
        rms.setAlwaysRemember(true);
        rms.setCookieName("signin");
        rms.setParameter("remember-me");
        // rms.setUseSecureCookie(env.acceptsProfiles("cloud"));
        rms.setUseSecureCookie(false);
        return rms;
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

            .and().authorizeRequests()
            .antMatchers(HttpMethod.GET, "/quizzes").hasAnyAuthority(Authority.user.getAuthority(), Authority.manager.getAuthority(), Authority.admin.getAuthority())

            .and().cors() //uncomment to pick up corsFilter bean

            .and().formLogin().loginProcessingUrl("/login")
            .defaultSuccessUrl("/userInfo")
            .loginPage("/loginFailed")

            .and().logout().deleteCookies("JSESSIONID")

            .and().rememberMe()
            .key(rememberMeKey)
            .rememberMeServices(rememberMeServices())
            .userDetailsService(userDetailsService())
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService())
            .passwordEncoder(getPasswordEncoder())
            .and().authenticationProvider(new RememberMeAuthenticationProvider("someKey"))
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
