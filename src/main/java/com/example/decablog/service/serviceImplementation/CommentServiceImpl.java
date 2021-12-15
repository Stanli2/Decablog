package com.example.decablog.service.serviceImplementation;

import com.example.decablog.model.Comments;
import com.example.decablog.repository.CommentRepository;
import com.example.decablog.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class CommentServiceImpl implements CommentServices {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public void createComment(Comments comments) {
        commentRepository.save(comments);
    }

    @Override
    public Comments getCommentById(Long id) {
        return commentRepository.findCommentsById(id);
    }

    @Override
    public void deleteComment(Comments comments) {
        commentRepository.delete(comments);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }
}
