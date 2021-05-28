package ute.group3.blogtravel.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<comment,String> {
    List<comment> findByUsername(String username);
    List<comment> findByPostId(Long postId);
}
