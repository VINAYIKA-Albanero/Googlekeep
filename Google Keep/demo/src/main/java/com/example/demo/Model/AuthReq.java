package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthReq {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public AuthReq(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    private String password;
}


//    public AuthReq(String username, String password) {
//        // Constructor code here
//    }
//
//    public AuthReq(String username, String password, String role) {
//        // Constructor code here
//    }






