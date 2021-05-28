package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comment {
    private String id;
    private int idPost;
    private String username;
    private String text;
    private Instant Created;
}
