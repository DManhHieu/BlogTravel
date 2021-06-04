package ute.group3.blogtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String userName;
    private String email;
    private Instant Created;
    private boolean Enabled;
    private String enable;
}
