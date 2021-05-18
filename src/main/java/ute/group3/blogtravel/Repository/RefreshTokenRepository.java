package ute.group3.blogtravel.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.RefreshToken;
import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken,String> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
