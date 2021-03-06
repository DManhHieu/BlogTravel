package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ute.group3.blogtravel.dto.AuthenticationResponse;


import ute.group3.blogtravel.dto.PostRequest;


import ute.group3.blogtravel.dto.PostResponse;
import ute.group3.blogtravel.dto.listPostResponse;
import ute.group3.blogtravel.model.ItemType;
import ute.group3.blogtravel.service.PostService;
import ute.group3.blogtravel.service.authenticationService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@AllArgsConstructor

public class PostController {
    private final PostService postService;
    private final authenticationService authentication;


    @GetMapping("/post/new")
    public String createPost(Model model, HttpSession session){
        if(!authentication.requestValid(session)){
            return "redirect:/login";
        }
        PostRequest post=new PostRequest();
        model.addAttribute("post", post);
        return "pages/post/newpost";
    }
    @PostMapping("/post/new")
    public String savePost(@ModelAttribute(value ="post") PostRequest postRequest, HttpSession session ) throws IOException {
        if(!authentication.requestValid(session)){
            return "redirect:/login";
        }
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        Long number = postService.save(postRequest,authentication.getUsername());
        return "redirect:/post/"+number.toString();
    }
    @GetMapping("/post/{number}")
    public String ViewPost(@PathVariable int number, Model model, HttpSession session){
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        String username="";
        if(authentication!=null){
            username=authentication.getUsername();
        }
        PostResponse postResponse= postService.getPost(number,username);
        if(postResponse==null){
            return "redirect:/";
        }
        model.addAttribute("itemtext", ItemType.TEXT);
        model.addAttribute("itemimg", ItemType.IMG);
        model.addAttribute("postResponse", postResponse);
        return "pages/post/viewpost";
    }
    @GetMapping({"/post/all","/post"})
    public String ListPosts(Model model){
        listPostResponse listPostResponse= postService.getAllPost();
        model.addAttribute("listPostResponse",listPostResponse);
        return "pages/post/listpost";
    }
    @GetMapping("post/my")
    public String MyPost(Model model, HttpSession session){
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        if(authentication==null){
            return "redirect:/login";
        }
        listPostResponse listPostResponse=postService.getPostByUser(authentication.getUsername());
        model.addAttribute("listPostResponse",listPostResponse);
        return "pages/post/listmypost";
    }

    @GetMapping("/post/{category}/list")
    public String ViewPost(@PathVariable String category, Model model, HttpSession session){
//        if(!authentication.requestValid(session)){
//            return "redirect:/post/{category}/list";
//        }
        listPostResponse postResponse = postService.getCategory(category);
        if(postResponse==null){
            return "redirect:/";
        }
        model.addAttribute("itemtext", ItemType.TEXT);
        model.addAttribute("itemimg", ItemType.IMG);
        model.addAttribute("postResponse", postResponse);
        model.addAttribute("category", category);

        return "/pages/post/category";
    }
}
