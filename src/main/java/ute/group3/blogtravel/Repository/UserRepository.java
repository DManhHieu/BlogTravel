package ute.group3.blogtravel.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.User;



public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    User findAllByUsername(String username);
}
