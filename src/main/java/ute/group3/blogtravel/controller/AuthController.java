package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ute.group3.blogtravel.dto.AuthenticationResponse;
import ute.group3.blogtravel.dto.LoginRequest;
import ute.group3.blogtravel.dto.RegisterRequest;
import ute.group3.blogtravel.service.AuthService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public String signup(@ModelAttribute(value = "registerRequest") RegisterRequest registerRequest, Model model){

        registerRequest = authService.signup(registerRequest);
        if(!registerRequest.isSuccess()){
            model.addAttribute("registerRequest",registerRequest);
            return "pages/Auth/register";
        }
        return "pages/Auth/registerSuccess";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setSuccess(false);
        model.addAttribute("registerRequest",registerRequest);
        return "pages/Auth/register";
    }
    @GetMapping("accountVerification/{token}")
    public String verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return "pages/Auth/ActiveAccount";
    }

    @GetMapping("/login")
    public String login(Model model){
        LoginRequest loginRequest=new LoginRequest();
        model.addAttribute("loginRequest", loginRequest);
        return "pages/Auth/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute(value = "loginRequest") LoginRequest loginRequest, Model model, HttpSession session){
        AuthenticationResponse authenticationResponse = authService.login(loginRequest);
        if(authenticationResponse.getAuthenticationToken().isEmpty()){
            model.addAttribute("loginRequest", authenticationResponse.getLoginRequest());
            return "pages/Auth/login";
        }
        session.setAttribute("authentication", authenticationResponse);
        return "redirect:/post";
    }
}
