package ute.group3.blogtravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ute.group3.blogtravel.Repository.UserRepository;
import ute.group3.blogtravel.model.User;

import java.util.List;


@Controller
public class GreetingController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping("/User")
    public String user(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "pages/listUser";
    }
}