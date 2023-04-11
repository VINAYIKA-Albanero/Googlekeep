package com.example.demo.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Document(collection = "folder")
public class FolderModel {
    @Id
    private  String _id;

    @NotBlank(message = "Enter valid userId")
    private String userId;

    @NotBlank(message = "Enter valid folderName")
    private String name;

    private List<String> roles;
}




