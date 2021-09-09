package makarov.learning.security;

import lombok.extern.slf4j.Slf4j;
import makarov.learning.model.User;
import makarov.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Slf4j
@Component
public class MyUserDetailsService_LoadUserByUsername implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        User u = user.orElseThrow(() -> new UsernameNotFoundException("username not found"));

        UserDetails ud = new MyUserDetails_UserSecurityDetailsService(u);
        log.info("user {} found",ud.getUsername());
        log.info("user's authorities: {}",ud.getAuthorities());
        return ud;
    }
}
