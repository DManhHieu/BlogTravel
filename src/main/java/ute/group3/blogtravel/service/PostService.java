package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ute.group3.blogtravel.Repository.PostRepository;
import ute.group3.blogtravel.dto.ItemPostRequest;
import ute.group3.blogtravel.dto.PostRequest;
import ute.group3.blogtravel.model.ItemPost;
import ute.group3.blogtravel.model.ItemType;
import ute.group3.blogtravel.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class PostService {
    private final PostRepository postRepository;
    private final FileUpLoadService fileUpLoadService;
    public void save(PostRequest postRequest, String user) throws IOException {
        Post post=new Post();
        post.setUsername(user);
        post.setDescription(postRequest.getDescription());
        if( postRepository.findTopByOrderByNumberDesc()==null){
            post.setNumber(0);
        }
        else {
            post.setNumber(postRepository.findTopByOrderByNumberDesc().getNumber() + 1);
        }
        List<ItemPost> items=new ArrayList<ItemPost>();
        int index=0;
        for (ItemPostRequest item: postRequest.getItemPost()
             ) {
            ItemPost itemPost=new ItemPost();
            itemPost.setNumber(index);
            itemPost.setType(item.getType());
            itemPost.setText(item.getText());
            if(item.getType()== ItemType.IMG){
                String filename=StringUtils.cleanPath(item.getImg().getOriginalFilename());
                itemPost.setText("postPhoto/"+post.getNumber()+"/"+StringUtils.cleanPath(item.getImg().getOriginalFilename()));
                fileUpLoadService.saveFile("postPhoto/"+post.getNumber(),filename,item.getImg());
            }
            items.add(itemPost);
            index++;
        }

        post.setItemPostList(items);
        postRepository.save(post);
    }
}
