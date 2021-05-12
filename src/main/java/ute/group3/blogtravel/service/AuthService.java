package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ute.group3.blogtravel.Repository.UserRepository;
import ute.group3.blogtravel.dto.RegisterRequest;
import ute.group3.blogtravel.model.User;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(RegisterRequest registerRequest) {
        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword( passwordEncoder.encode(registerRequest.getPassword())); // mã hoá password
        user.setCreated(Instant.now());
        user.setEnabled(false);

        // lưu vào database
        userRepository.save(user);


    }
}
