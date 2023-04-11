package com.example.demo.Repository;

import com.example.demo.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String> {
    @Query
    Optional<UserModel> findByName(String fullName);
}







