package makarov.learning.controller;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*") //on a class ok
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


}
