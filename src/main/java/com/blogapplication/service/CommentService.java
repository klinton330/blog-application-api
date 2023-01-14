package com.blogapplication.service;

import com.blogapplication.entity.Comment;
import com.blogapplication.payload.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    public CommentDTO createComment(long id, CommentDTO commentDTO);

    public List<CommentDTO> getAllPost(long postId);

    public CommentDTO getCommentById(Long postId,Long commentId);

    public CommentDTO updateComment(long postId,long commentId,CommentDTO commentDTO);

    public void deleteComment(Long postId,Long commentId);

}
