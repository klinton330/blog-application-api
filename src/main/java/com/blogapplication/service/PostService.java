package com.blogapplication.service;

import com.blogapplication.payload.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDto);
    List<PostDTO> getAllPost();

    PostDTO getPostById(long id);

    PostDTO updatePostById(PostDTO post,long id);

    void deletePostById(long id);
}
