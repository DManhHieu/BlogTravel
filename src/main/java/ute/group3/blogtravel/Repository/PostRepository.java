package ute.group3.blogtravel.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ute.group3.blogtravel.model.Post;

import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post,String> {
    Post findTopByOrderByNumberDesc();
    Post findByNumber(int number);
    List<Post> findByUsername(String username);
}
