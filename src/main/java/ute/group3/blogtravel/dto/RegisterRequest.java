package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private String emailError;
    private String userError;
    private String fullName;
    private String address;
    private String datetime;
    private String gender;
    private String passwordError;
    private boolean success;
}
