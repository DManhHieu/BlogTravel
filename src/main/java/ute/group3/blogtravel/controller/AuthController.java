package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ute.group3.blogtravel.dto.RegisterRequest;
import ute.group3.blogtravel.model.User;
import ute.group3.blogtravel.service.AuthService;

@Controller
@AllArgsConstructor
@RequestMapping("/auth/")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public String signup(@ModelAttribute(value = "registerRequest") RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return "pages/home";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        RegisterRequest registerRequest=new RegisterRequest();
        model.addAttribute("registerRequest",registerRequest);
        return "pages/register";
    }
}
