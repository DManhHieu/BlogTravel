package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id;
    @Indexed(unique = true)
    private long number;
    private String username;
    private String headerImg;
    private String Title;
    private String description;
    private Instant Created;
    private List<ItemPost> itemPostList;
}
