package com.blogapplication.service;

import com.blogapplication.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDto);
    List<PostDTO> getAllPost(int pageNo,int pageSize);

    PostDTO getPostById(long id);

    PostDTO updatePostById(PostDTO post,long id);

    void deletePostById(long id);
}
