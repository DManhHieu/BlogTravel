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
    public Long save(PostRequest postRequest, String user) throws IOException {
        Post post=new Post();
        post.setUsername(user);
        post.setCategory(postRequest.getCategory());
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setCreated(Instant.now());
        post.setBrowser(false);
        if( postRepository.findTopByOrderByNumberDesc()==null){
            post.setNumber(0);
        }
        else {
            post.setNumber(postRepository.findTopByOrderByNumberDesc().getNumber() + 1);
        }

        String fileimgheader=StringUtils.cleanPath(postRequest.getImgHeader().getOriginalFilename());
        if(!fileimgheader.equals("")) {
            post.setHeaderImg("img/postPhoto/"+post.getNumber() + "/" + fileimgheader);
            fileUpLoadService.saveFile("img/postPhoto/" + post.getNumber(), fileimgheader, postRequest.getImgHeader());
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
                        itemPost.setText("img/postPhoto/"+post.getNumber() + "/" + filename);
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
        return post.getNumber();
    }

    public PostResponse getPost(int number, String user) {
        Post post=postRepository.findByNumber(number);
        if(post==null) return null;
        if(!post.isBrowser() && !post.getUsername().equals(user)) {
            return null;
        }
        return  MapPostToPostResponse(post);
    }

    public PostResponse getPostForAdmin(int number, String user) {
        Post post=postRepository.findByNumber(number);
        return  MapPostToPostResponse(post);
    }
    private PostResponse MapPostToPostResponse(Post post){
        PostResponse postResponse=new PostResponse();
        postResponse.setItemPosts(post.getItemPostList());
        postResponse.setCategory(post.getCategory());
        postResponse.setCreated(post.getCreated());
        postResponse.setNumber(post.getNumber());
        postResponse.setTitle(post.getTitle());
        postResponse.setImgHeader(post.getHeaderImg());
        postResponse.setDescription(post.getDescription());
        postResponse.setAuthorName(userRepository.findByUsername(post.getUsername()).getFullName());
        postResponse.setBrowser(post.isBrowser());
        return postResponse;
    }
    public listPostResponse getAllPost() {
        List<PostResponse> postResponses=new ArrayList<>();
        List<Post> listPost= postRepository.findAllByBrowserIsTrue();
        for (Post post: listPost
             ) {
            postResponses.add(MapPostToPostResponse(post));

        }
        return new listPostResponse(postResponses);
    }
    public listPostResponse getAllPostForAdmin() {
        List<PostResponse> postResponses=new ArrayList<>();
        List<Post> listPost= postRepository.findAll();
        for (Post post: listPost) {
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

    public List<PostResponse> getTopThree() {
        List<PostResponse> postResponses=new ArrayList<>();
        List<Post> listPost= postRepository.findAllByBrowserIsTrue();
        for (Post post: listPost
        ) {
            postResponses.add(MapPostToPostResponse(post));
        }
        if(postResponses.size()<3)
            return postResponses;
        return postResponses.subList(0,3);
    }

    public PostResponse getTrending() {
        return getPost(13,"");
    }
    public void BrowserPost(int number){
        Post post= postRepository.findByNumber(number);
        post.setBrowser(true);
        postRepository.save(post);
    }

    public void DeletePost(int number){
        Post post= postRepository.findByNumber(number);
        postRepository.delete(post);
    }

    public listPostResponse getCategory(String category) {
        List<PostResponse> postResponses=new ArrayList<>();
        List<Post> postCategory = postRepository.findByCategory(category);
        for (Post post: postCategory
        ) {
            postResponses.add(MapPostToPostResponse(post));

        }
        return new listPostResponse(postResponses);
    }
}
