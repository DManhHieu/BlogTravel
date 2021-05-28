package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class topic {
    private String id;
    private String name;
    private String description;
}
