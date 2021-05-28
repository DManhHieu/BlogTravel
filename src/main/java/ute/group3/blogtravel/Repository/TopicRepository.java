package ute.group3.blogtravel.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ute.group3.blogtravel.model.topic;

public interface TopicRepository extends MongoRepository<topic,String> {
}
