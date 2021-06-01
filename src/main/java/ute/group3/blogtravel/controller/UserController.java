package ute.group3.blogtravel.controller;
import lombok.AllArgsConstructor;
import org.springframework.security.config.web.servlet.headers.HttpPublicKeyPinningDsl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ute.group3.blogtravel.dto.AuthenticationResponse;
import ute.group3.blogtravel.dto.PostRequest;
import ute.group3.blogtravel.dto.UserDTO;
import ute.group3.blogtravel.model.User;
import ute.group3.blogtravel.service.UserService;
import ute.group3.blogtravel.service.authenticationService;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final authenticationService authenticationn;
    @GetMapping("/user")
    public String ViewUser(Model model, HttpSession session){
        if(!authenticationn.requestValid(session)){
            return "redirect:/login";
        }
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        String username="";
        if(authentication!=null){
            username=authentication.getUsername();
        }
        UserDTO userDTO = userService.getUser(username);
        if (userDTO == null){
            return "redirect:/";
        }
        model.addAttribute("userDTO", userDTO);
        return "/pages/user/user";
    }


    @GetMapping("/user/edit")
    public String saveUser(Model model, HttpSession session){

        if(!authenticationn.requestValid(session)){
            return "redirect:/login";
        }
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        String username= "";
        if(authentication!=null){
            username=authentication.getUsername();
        }
        UserDTO userDTO = userService.getUser(username);
        if (userDTO == null){
            return "redirect:/";
        }
        model.addAttribute("userUpdate", userDTO);
        return "pages/user/edituser";
    }

    @PostMapping("/user")
    public String saveUser(@ModelAttribute(value ="userUpdate") UserDTO userDTO, HttpSession session, Model model ) throws IOException {
        if(!authenticationn.requestValid(session)){
            return "redirect:/login";
        }
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        String username= "";
        if(authentication!=null){
            username=authentication.getUsername();
        }
        UserDTO userDTOo = userService.getUser(username);
        userDTO = userService.UpdateUser(userDTO, username);
        model.addAttribute("userUpdate",userDTO);
        return "redirect:/user";
    }

}
