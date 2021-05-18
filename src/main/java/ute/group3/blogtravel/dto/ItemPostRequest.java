package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ute.group3.blogtravel.model.ItemType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPostRequest {
    private String text;
    private MultipartFile img;
    private ItemType type;
    private int number;
}
