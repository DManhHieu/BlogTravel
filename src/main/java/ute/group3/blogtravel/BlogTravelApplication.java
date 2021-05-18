package ute.group3.blogtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication
@EnableAsync
public class BlogTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogTravelApplication.class, args);
    }
}
