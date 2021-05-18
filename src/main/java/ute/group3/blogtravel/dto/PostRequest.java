package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String Title;
    private String description;
    private List<ItemPostRequest> itemPost;
}
