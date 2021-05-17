package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ute.group3.blogtravel.dto.LoginRequest;

import ute.group3.blogtravel.dto.PostRequest;
import ute.group3.blogtravel.model.ItemPost;
import ute.group3.blogtravel.model.ItemType;
import ute.group3.blogtravel.model.Post;

import ute.group3.blogtravel.service.PostService;
import ute.group3.blogtravel.service.authenticationService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor

public class PostController {
    private final PostService postService;
    private final authenticationService authentication;
    @GetMapping("/newpost")
    public String createPost(Model model, HttpSession session){
        if(!authentication.requestValid(session)){
            LoginRequest loginRequest=new LoginRequest();
            model.addAttribute("loginRequest", loginRequest);
            return "pages/Auth/login";
        }
        PostRequest post=new PostRequest();
        model.addAttribute("post", post);
        return "pages/post/newpost";
    }
    @PostMapping("/newpost")
    public String savePost(@ModelAttribute(value ="post") PostRequest postRequest){

        return "pages/home";
    }
}
