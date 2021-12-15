package com.example.decablog.repository;

import com.example.decablog.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    Comments findCommentsById(Long id);
}
