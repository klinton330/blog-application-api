package com.blogapplication.service;

import com.blogapplication.payload.PostDTO;
import com.blogapplication.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDto);
    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePostById(PostDTO post,long id);

    void deletePostById(long id);
}
