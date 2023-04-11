package com.example.demo.Controller;
import com.example.demo.Model.AuthReq;
import com.example.demo.Model.UserModel;
import com.example.demo.Services.JWTService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class UserController {


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/addData/register")
    public ResponseEntity<?> createUser(@RequestBody UserModel userData){
        logger.info("started user creation");
        UserModel response = userService.createUser(userData);
        logger.info("user creation done");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsers(){
        logger.info("getting data of all users");
        List<UserModel> response = userService.getAllUsers();
        if(response == null){
            logger.warn("through error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("completed retriving of all users");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //getUserById
    @GetMapping("/GetData/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        logger.info(" get data through user by id");
        UserModel response = userService.getUserById(id);
        if(response == null){
            logger.warn("throwing error");
            return new ResponseEntity<>("No such users",HttpStatus.NOT_FOUND);
        }
        logger.info("Completed user by id");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/authentication")
    public String authenticateAndGetToken(@RequestBody AuthReq authReq){
        logger.info("  token generating");
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authReq.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid request");
        }

     }

}















