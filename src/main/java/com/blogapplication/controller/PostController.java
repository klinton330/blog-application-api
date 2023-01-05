package com.blogapplication.controller;

import com.blogapplication.payload.PostDTO;
import com.blogapplication.service.PostService;
import net.bytebuddy.asm.Advice;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDTO> getAllPost()
    {
        return postService.getAllPost() ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO>getPostById(@PathVariable long id)
    {
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO>updatePost(@RequestBody PostDTO postDto,@PathVariable long id)
    {
        return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String>deletePost(@PathVariable long id)
    {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully",HttpStatus.OK);
    }
}
