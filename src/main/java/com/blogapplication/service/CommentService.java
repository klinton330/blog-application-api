package com.blogapplication.service;

import com.blogapplication.entity.Comment;
import com.blogapplication.payload.CommentDTO;
import org.springframework.stereotype.Service;


public interface CommentService {
    public CommentDTO createComment(long id, CommentDTO commentDTO);
}
