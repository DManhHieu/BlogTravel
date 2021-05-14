package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemPost {
    private String text;
    private ItemType type;
    private int number;
}
enum ItemType{
    IMG, TEXT
}