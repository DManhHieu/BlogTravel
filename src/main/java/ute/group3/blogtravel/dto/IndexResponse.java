package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ute.group3.blogtravel.model.topic;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndexResponse {
    private List<PostResponse> listPost;
    private PostResponse PostTrending;
    private List<topic> listTopic;
}
