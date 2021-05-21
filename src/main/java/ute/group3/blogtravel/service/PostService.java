package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ute.group3.blogtravel.Repository.PostRepository;
import ute.group3.blogtravel.Repository.UserRepository;
import ute.group3.blogtravel.dto.ItemPostRequest;
import ute.group3.blogtravel.dto.PostRequest;
import ute.group3.blogtravel.dto.PostResponse;
import ute.group3.blogtravel.dto.listPostResponse;
import ute.group3.blogtravel.model.ItemPost;
import ute.group3.blogtravel.model.ItemType;
import ute.group3.blogtravel.model.Post;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class PostService {
    private final PostRepository postRepository;
    private final FileUpLoadService fileUpLoadService;
    private final UserRepository userRepository;
    public void save(PostRequest postRequest, String user) throws IOException {
        Post post=new Post();
        post.setUsername(user);
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setCreated(Instant.now());
        if( postRepository.findTopByOrderByNumberDesc()==null){
            post.setNumber(0);
        }
        else {
            post.setNumber(postRepository.findTopByOrderByNumberDesc().getNumber() + 1);
        }
        if(postRequest.getItemPost()!=null)
        {
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
                    if(!filename.equals("")) {
                        itemPost.setText("img/postPhoto/"+post.getNumber() + "/" + StringUtils.cleanPath(item.getImg().getOriginalFilename()));
                        fileUpLoadService.saveFile("img/postPhoto/" + post.getNumber(), filename, item.getImg());
                        items.add(itemPost);
                        index++;
                    }
                } else {
                    items.add(itemPost);
                    index++;
                }
            }

            post.setItemPostList(items);
        }
        postRepository.save(post);
    }

    public PostResponse getPost(int number) {
        PostResponse postResponse=new PostResponse();
        Post post=postRepository.findByNumber(number);
        postResponse.setItemPosts(post.getItemPostList());
        postResponse.setCreated(post.getCreated());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setAuthorName(userRepository.findByUsername(post.getUsername()).getFullName());
        return  postResponse;
    }
    private PostResponse MapPostToPostResponse(Post post){
        PostResponse postResponse=new PostResponse();
        postResponse.setItemPosts(post.getItemPostList());
        postResponse.setCreated(post.getCreated());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setAuthorName(userRepository.findByUsername(post.getUsername()).getFullName());
        return postResponse;
    }
    public listPostResponse getAllPost() {
        List<PostResponse> postResponses=new ArrayList<>();
        List<Post> listPost= postRepository.findAll();
        for (Post post: listPost
             ) {
            postResponses.add(MapPostToPostResponse(post));

        }
        return new listPostResponse(postResponses);
    }
    public listPostResponse getPostByUser(String username){
        List<PostResponse> postResponses=new ArrayList<>();
        List<Post> listPost= postRepository.findByUsername(username);
        for (Post post: listPost
        ) {
            postResponses.add(MapPostToPostResponse(post));

        }
        return new listPostResponse(postResponses);
    }
}
