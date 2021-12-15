package com.example.decablog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 65535, columnDefinition = "text")
    private String intro;
    @Column(nullable = false, length = 65535, columnDefinition = "text")
    private String content;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime date_created = LocalDateTime.now();
}
