package ute.group3.blogtravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String id;
    private String token;
    private Instant createdDate;
}
