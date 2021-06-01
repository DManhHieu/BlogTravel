package ute.group3.blogtravel.service;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ute.group3.blogtravel.Repository.UserRepository;
import ute.group3.blogtravel.Repository.VerificationTokenRepository;
import ute.group3.blogtravel.dto.AuthenticationResponse;
import ute.group3.blogtravel.dto.LoginRequest;
import ute.group3.blogtravel.dto.RegisterRequest;
import ute.group3.blogtravel.model.NotificationEmail;
import ute.group3.blogtravel.model.User;
import ute.group3.blogtravel.model.VerificationToken;
import ute.group3.blogtravel.security.JwtProvider;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final JwtProvider jwtProvider;
    private final VerificationTokenRepository verificationTokenRepository;

    public RegisterRequest signup(RegisterRequest registerRequest) {
        registerRequest.setEmailError("");
        registerRequest.setPasswordError("");
        registerRequest.setUserError("");
        if(userRepository.findByUsername(registerRequest.getUsername())!=null){
            System.out.println("Tên tài khoản đã tồn tại");
            registerRequest.setUserError("Tên tài khoản đã tồn tại");
            return registerRequest;
        }
        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword( passwordEncoder.encode(registerRequest.getPassword())); // mã hoá password
        user.setCreated(Instant.now());
//        String a = "";
//        user.setGender(a);
//        user.setFullName(a);
//        user.setDatetime(a);
//        user.setAddress(a);
        user.setEnabled(false);

        // lưu vào database
        userRepository.save(user);
        // tạo token
        String token= generateVerificationToken(user);
        System.out.println("Gửi mail kích hoạt tới: "+user.getEmail());
        // Gửi mail kích hoạt tài khoản
        mailService.sendMail(new NotificationEmail("Hãy kích hoạt tài khoản", user.getEmail(), "Cảm ưn bạn đã đăng kí Blog Travel " +
                "hãy click vào url dưới đây để kích hoạt tài khoản: " +
                "http://localhost:8080/accountVerification/" + token ));
        registerRequest.setSuccess(true);
        return registerRequest;
    }

    private String generateVerificationToken(User user) { // hàm tạo và lưu token cho user
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsername(user.getUsername());
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken==null){
            System.out.println("Không tìm thấy token: "+ token);
            return;
        }
        fetchUserAndEnable(verificationToken);
    }
    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        String username=verificationToken.getUsername();
        User user= userRepository.findByUsername(username);
        if(user==null){
            System.out.println("Không tìm thấy tài khoản: "+ username);
            return;
        }
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        User user= userRepository.findByUsername(loginRequest.getUsername());
        if(user==null) {

            loginRequest.setUsernameError("Không tìm thấy tài khoản");
            return new AuthenticationResponse("", "",loginRequest);
        }
        if(user.getEnabled()==false){
            loginRequest.setUsernameError("Tài khoản chưa kích hoạt");
            return new AuthenticationResponse("", "",loginRequest);
        }
        if(user.getPassword().equals( passwordEncoder.encode(loginRequest.getPassword()))){
            loginRequest.setPasswordError("Sai mật khẩu");
            return new AuthenticationResponse("", "",loginRequest);
        }
        System.out.println("Tài khoản: "+loginRequest.getUsername()+" đăng nhập thành công");
        String token = jwtProvider.generateTokenWithUserName(loginRequest.getUsername());
        return new AuthenticationResponse(token, loginRequest.getUsername(),null);
    }
}
