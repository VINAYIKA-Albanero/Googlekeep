package com.example.demo.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@Document(collation ="user")
public class UserModel {

    @Id
    private String _id;

    @NotBlank
    private String name;
    @NotBlank
    private int mobileNumber;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String roles;
    private List<FolderModel> folders;


}




