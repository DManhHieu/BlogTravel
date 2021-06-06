package ute.group3.blogtravel.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.Post;

import java.util.List;

public interface PostRepository extends MongoRepository<Post,String> {
    Post findTopByOrderByNumberDesc();
    Post findByNumber(int number);
    List<Post> findByUsername(String username);
    List<Post> findAllByBrowserIsTrue();
    List<Post> findByCategory(String category);
}
