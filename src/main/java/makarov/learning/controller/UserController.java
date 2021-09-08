package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
