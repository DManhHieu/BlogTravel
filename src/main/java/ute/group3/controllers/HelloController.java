package ute.group3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {
    @RequestMapping("hello")
    public void printHello() {
        System.out.println("Im here");
    }
}
