package ute.group3.blogtravel.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.VerificationToken;


public interface VerificationTokenRepository extends MongoRepository<VerificationToken,String> {
    VerificationToken findByToken(String token);
}
