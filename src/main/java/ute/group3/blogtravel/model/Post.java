package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id;
    private String Title;
    private String description;
    private Optional<ItemPost> itemPostList;
}
