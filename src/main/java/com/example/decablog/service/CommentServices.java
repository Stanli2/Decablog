package com.example.decablog.service;

import com.example.decablog.model.Comments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentServices {

    void createComment(Comments comments);

    Comments getCommentById(Long id);

    void deleteComment(Comments comments);

    List<Comments> getAllComments();
}
