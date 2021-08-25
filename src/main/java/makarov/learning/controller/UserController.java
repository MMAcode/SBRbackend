package makarov.learning.controller;

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
@RequestMapping("api/")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("users")
    //http://localhost:8080/api/users
    public List<User> getUsers(){
        return userRepository.findAll();
    }


}
