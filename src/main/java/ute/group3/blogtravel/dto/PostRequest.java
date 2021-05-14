package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ute.group3.blogtravel.model.ItemPost;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String Title;
    private String description;
    private List<ItemPost> itemPost;
}
