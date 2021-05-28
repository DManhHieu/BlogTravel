package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ute.group3.blogtravel.Repository.CommentRepository;
import ute.group3.blogtravel.dto.CommentDto;
import ute.group3.blogtravel.model.comment;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public void save(CommentDto commentDto) {
        comment comment=new comment();
        comment.setCreated(Instant.now());
        comment.setPostId(commentDto.getPostId());
        comment.setText(commentDto.getText());
        comment.setUsername(comment.getUsername());
        commentRepository.save(comment);
    }
    public CommentDto MapCommentToCommentDTO(comment comment){
        CommentDto commentDto=new CommentDto();
        commentDto.setUserName(comment.getUsername());
        commentDto.setPostId(comment.getPostId());
        commentDto.setText(comment.getText());
        return commentDto;
    }
    public List<CommentDto> getAllCommentsForPost(Long postId) {
        List<CommentDto> listComment=new ArrayList<>();
        for (comment comment:   commentRepository.findByPostId(postId)
        ) {
            listComment.add(MapCommentToCommentDTO(comment));

        }
        return listComment;
    }
}
