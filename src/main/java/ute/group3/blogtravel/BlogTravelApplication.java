package ute.group3.blogtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ute.group3.blogtravel.Repository.UserRepository;


@SpringBootApplication
public class BlogTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogTravelApplication.class, args);
    }
}
