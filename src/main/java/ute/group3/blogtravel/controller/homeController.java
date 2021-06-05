package ute.group3.blogtravel.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ute.group3.blogtravel.dto.IndexResponse;
import ute.group3.blogtravel.dto.listPostResponse;
import ute.group3.blogtravel.model.ItemType;
import ute.group3.blogtravel.service.PostService;
import ute.group3.blogtravel.service.TopicService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class homeController {
    private final PostService postService;
    private final TopicService topicService;
    @GetMapping("/")
    public String home(Model model, HttpSession session){
        IndexResponse indexResponse=new IndexResponse();
        indexResponse.setListPost(postService.getTopThree());
        indexResponse.setPostTrending(postService.getTrending());
        indexResponse.setListTopic(topicService.getAllTopic());
        model.addAttribute("indexResponse",indexResponse);
        if(session.getAttribute("isLogin")==null){
            session.setAttribute("isLogin",false);
        }
        return "pages/index";
    }

//    @PostMapping("/post/{category}")
//    public String ViewPost(@PathVariable String category, Model model, HttpSession session){
////        AuthenticationResponse authentication=(Authenticatio        String username="";
////        if(authentication!=null){
////            username=authentication.getUsername();
////        }nResponse) session.getAttribute("authentication");
////
//        listPostResponse postResponse = postService.getCategory(category);
//        if(postResponse==null){
//            return "redirect:/";
//        }
//        model.addAttribute("itemtext", ItemType.TEXT);
//        model.addAttribute("itemimg", ItemType.IMG);
//        model.addAttribute("postResponse", postResponse);
//        return "pages/index";
//    }
}
