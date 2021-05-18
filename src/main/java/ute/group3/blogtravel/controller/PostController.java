package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ute.group3.blogtravel.dto.AuthenticationResponse;


import ute.group3.blogtravel.dto.PostRequest;


import ute.group3.blogtravel.service.PostService;
import ute.group3.blogtravel.service.authenticationService;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@AllArgsConstructor

public class PostController {
    private final PostService postService;
    private final authenticationService authentication;
    @GetMapping("/newpost")
    public String createPost(Model model, HttpSession session){
        if(!authentication.requestValid(session)){
            return "redirect:/login";
        }
        PostRequest post=new PostRequest();
        model.addAttribute("post", post);
        return "pages/post/newpost";
    }
    @PostMapping("/newpost")
    public String savePost(@ModelAttribute(value ="post") PostRequest postRequest, HttpSession session, Model model ) throws IOException {
        if(!authentication.requestValid(session)){
            return "redirect:/login";
        }
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        postService.save(postRequest,authentication.getUsername());
        return "pages/home";
    }
}
