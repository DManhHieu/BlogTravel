package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "user")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String fullName;

    @Indexed(unique = true)
    private String username;

    private String password;

    private String email;
    private Instant Created;
    private Boolean Enabled;
    private String address;
    private String datetime;
    private String gender;
    @Override
    public String toString(){
        return "id="+id+" Fullname="+fullName;
    }

}
