package ute.group3.blogtravel.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ute.group3.blogtravel.dto.PostResponse;
import ute.group3.blogtravel.dto.listPostResponse;
import ute.group3.blogtravel.model.ItemType;
import ute.group3.blogtravel.service.PostService;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/admin")
public class ManagerPostController {

    private final PostService postService;

    @GetMapping({"/allpost"})
    public String ListPosts(Model model){
        listPostResponse listPostResponse= postService.getAllPostForAdmin();
        model.addAttribute("listPostResponse",listPostResponse);
        return "pages/admin/ManagerPost";
    }

    @RequestMapping(value = "/allpost/detailpost")
    public String GetPostByNumber(Model model, @RequestParam(value = "number") int number, @RequestParam(value = "username") String username ){
        model.addAttribute("itemtext", ItemType.TEXT);
        model.addAttribute("itemimg", ItemType.IMG);
        PostResponse postResponse= postService.getPostForAdmin(number, username);
        model.addAttribute("post", postResponse);
        return "pages/admin/detailpost";
    }

    @RequestMapping(value = "/allpost/detailpost/browserpost/{number}")
    public String BroserPost(@PathVariable("number") int number){
        postService.BrowserPost(number);
        return "redirect:/admin/allpost";
    }

    @RequestMapping(value ="/detailpost/deletepost/{number}")
    public String DeletePost(@PathVariable("number") int number){
        postService.DeletePost(number);
        return "redirect:/admin/allpost";
    }
}
