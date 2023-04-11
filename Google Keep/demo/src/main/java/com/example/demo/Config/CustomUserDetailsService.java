package com.example.demo.Config;
        import com.example.demo.Model.UserModel;
        import com.example.demo.Repository.UserRepository;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Component;

        import java.util.Optional;


@Component
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
   @Autowired
   UserRepository userRepository;


    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        logger.error("in custom user details service");
        Optional<UserModel> userOptional = userRepository.findByName(name);
        logger.error("got the data");
        return userOptional.map(CustomUserService :: new).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}




















