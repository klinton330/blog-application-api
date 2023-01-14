package com.blogapplication.controller;

import com.blogapplication.entity.Comment;
import com.blogapplication.payload.CommentDTO;
import com.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDTO> createNewComment(@PathVariable(value = "postid") long postid, @RequestBody CommentDTO commentDTO)
    {
        return new ResponseEntity<>(commentService.createComment(postid,commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO>getAllComments(@PathVariable(value = "postId") long postId)
    {
        return commentService.getAllPost(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO>getCommentById(@PathVariable(value = "postId") long postId,@PathVariable(value = "id")long commentId)
    {
        CommentDTO commentDTO=commentService.getCommentById(postId,commentId);
        return new ResponseEntity(commentDTO,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO>updateComment(@PathVariable(value = "postId") long postId,@PathVariable(value = "id")long commentId,@RequestBody CommentDTO commentDTO)
    {
        return new ResponseEntity<>(commentService.updateComment(postId,commentId,commentDTO),HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable(value = "postId") long postId,@PathVariable(value = "id")long commentId)
    {
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment Deleted Successfully",HttpStatus.OK);
    }
}
