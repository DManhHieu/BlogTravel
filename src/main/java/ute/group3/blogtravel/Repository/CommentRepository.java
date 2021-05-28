package ute.group3.blogtravel.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.comment;

public interface CommentRepository extends MongoRepository<comment,String> {
}
