package com.blogapplication.controller;

import com.blogapplication.entity.Comment;
import com.blogapplication.payload.CommentDTO;
import com.blogapplication.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postid}/comments")

    public ResponseEntity<CommentDTO> createNewComment( @PathVariable(value = "postid") long postid, @Valid @RequestBody CommentDTO commentDTO)
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
    public ResponseEntity<CommentDTO>updateComment(@PathVariable(value = "postId") long postId,@PathVariable(value = "id")long commentId,@Valid @RequestBody CommentDTO commentDTO)
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
