package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ute.group3.blogtravel.dto.listPostResponse;
import ute.group3.blogtravel.service.PostService;

@Controller
@AllArgsConstructor
public class homeController {
    private final PostService postService;
    @GetMapping("/")
    public String home(Model model){
        listPostResponse listPostResponse= postService.getAllPost();
        if(listPostResponse.getListPost().size()>3)
            listPostResponse.setListPost(listPostResponse.getListPost().subList(0,3));
        model.addAttribute("listPostResponse",listPostResponse);
        return "pages/index";
    }
}
