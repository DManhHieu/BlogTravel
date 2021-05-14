package ute.group3.blogtravel.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.Post;

public interface PostRepository extends MongoRepository<Post,String> {
}
