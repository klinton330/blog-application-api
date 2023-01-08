package com.blogapplication.controller;

import com.blogapplication.entity.Comment;
import com.blogapplication.payload.CommentDTO;
import com.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
