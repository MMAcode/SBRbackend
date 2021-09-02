package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.MMUser;
import makarov.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins="*") //on a class ok
@RequestMapping({"","/","api/"}) // @RequestMapping("api/")
@Slf4j
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("users")
    public Iterable<MMUser> getUsers(){
        log.info("ahoj from logger");
        return userRepository.findAll();
    }

    @GetMapping("user/username/{username}")
    public Optional<MMUser> getUser(@PathVariable String username){
        return userRepository.findByUsername(username);
    }


}
