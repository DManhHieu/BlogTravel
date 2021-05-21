package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String Title;
    private String description;
    private MultipartFile imgHeader;
    private List<ItemPostRequest> itemPost;
}
