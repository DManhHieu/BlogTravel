package ute.group3.blogtravel.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ute.group3.blogtravel.model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

}
