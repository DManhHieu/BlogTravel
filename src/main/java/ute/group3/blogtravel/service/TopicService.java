package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ute.group3.blogtravel.Repository.TopicRepository;
import ute.group3.blogtravel.model.topic;

import java.util.List;
@Service
@AllArgsConstructor

public class TopicService {
    private final TopicRepository topicRepository;
    public List<topic> getAllTopic() {
        return topicRepository.findAll();
    }
}
