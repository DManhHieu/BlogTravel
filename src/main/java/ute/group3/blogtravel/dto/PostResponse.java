package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ute.group3.blogtravel.model.ItemPost;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String Title;
    private String authorName;
    private String description;
    private Instant Created;
    private List<ItemPost> itemPosts;
}
