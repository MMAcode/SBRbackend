package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
// @CrossOrigin(origins="") //on a class ok
// @CrossOrigin(origins={"*","http://localhost:8080","http://localhost:3000"}) //on a class ok
@RequestMapping({"","/","api/"}) // @RequestMapping("api/")
@Slf4j
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("users")
    public Iterable<User> getUsers(){
        log.info("ahoj from logger");
        return userRepository.findAll();
    }

    @GetMapping("user/username/{username}")
    public Optional<User> getUser(@PathVariable String username){
        return userRepository.findByUsername(username);
    }

    @GetMapping(path = "/basicauth")
    public String helloWorldBean() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return "You are authenticated";
    }

    // @GetMapping("/login")
    // public Object sendUserRoleOrError (Authentication authentication, Principal principal){
    //     // if(authentication.isAuthenticated()){
    //         // String role = authentication.getAuthorities()
    //     return principal;
    // }

    @GetMapping("/userInfo")
    public Object sendUserRoleOrErro (Authentication authentication, Principal principal){
        // System.out.println("postloginreached");
        // if(authentication.isAuthenticated()){
        // String role = authentication.getAuthorities()
        return principal;
    }

    @GetMapping(value="/loginFailed",produces= MediaType.TEXT_PLAIN_VALUE)
    public String sendUserError (){
        // System.out.println("postloginreached");
        // if(authentication.isAuthenticated()){
        // String role = authentication.getAuthorities()
        return "wrong credentials";
    }

    // @GetMapping("/loginFailed")
    // public ResponseEntity<String> sendUserError() {
    //     HttpHeaders responseHeaders = new HttpHeaders();
    //     responseHeaders.set("Baeldung-Example-Header",
    //         "Value-ResponseEntityBuilderWithHttpHeaders");
    //
    //     return ResponseEntity.ok()
    //         .headers(responseHeaders)
    //         .body("Response with header using ResponseEntity");
    // }

}
