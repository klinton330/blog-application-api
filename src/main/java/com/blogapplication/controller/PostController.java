package com.blogapplication.controller;

import com.blogapplication.payload.PostDTO;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.service.PostService;
import com.blogapplication.utils.AppConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/v1/posts")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
    @GetMapping("/v1/posts")
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir)
    {
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir) ;
    }

    @GetMapping(value = "/v1/posts/{id}")
    public ResponseEntity<PostDTO>getPostById(@PathVariable long id)
    {
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }


    @PutMapping("/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO>updatePost(@Valid@RequestBody PostDTO postDto,@PathVariable long id)
    {
        return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);
    }
    @DeleteMapping("/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<String>deletePost(@PathVariable long id)
    {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully",HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/catagory/1
    @GetMapping("/v1/posts/catagory/{id}")
    public  ResponseEntity<List<PostDTO>>getPostByCatagory(@PathVariable("id") Long catagoryId)
    {
        List<PostDTO> postDTOS=postService.getPostByCatagory(catagoryId);
        return ResponseEntity.ok(postDTOS);
    }
}
