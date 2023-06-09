package com.example.demo.Services;

import com.example.demo.Model.UserModel;
import com.example.demo.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class UserService {

    @Bean
    public PasswordEncoder passwordEncoder(){

        return  new BCryptPasswordEncoder();
   }


    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);





    public UserModel createUser(UserModel userData) {
        logger.info("Creating user");
        userData.setPassword(passwordEncoder().encode(userData.getPassword()));
        userData.setFolders(Collections.emptyList());
        logger.info("user created successfully");
        return userRepository.save(userData);
    }






    public List<UserModel> getAllUsers() {
        logger.info("Getting all users");
        List<UserModel> users = userRepository.findAll();

        users.stream().map(user->{
            ResponseEntity<List> folders = webClient
                    .build()
                    .get()
                    .uri("http://localhost:8084/folders/user/"+user.get_id())
                    .retrieve()
                    .toEntity(List.class)
                    .block();
            user.setFolders(folders.getBody());
            System.out.println(user);
            return user;
        }).collect(Collectors.toList());

        if(users.size()==0) {
            logger.warn("No users found");
            return null;
        }
        logger.info("users found");
        return users;
    }







    public UserModel getUserById(String id) {
        logger.info("Getting user by id");
        Optional<UserModel> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()) {
            logger.info("user found");
            UserModel user = userOptional.get();
            logger.info("getting folders for user");
            ResponseEntity<List> folders;
            folders = webClient
                    .build()
                    .get()
                    .uri("http://localhost:8084/folders/user/" + id)
                    .retrieve()
                    .toEntity(List.class)
                    .block();
            logger.info("folders found for the user");
            user.setFolders(folders.getBody());
            return user;
        }
        logger.warn("user not found");
        return null;
    }
}








