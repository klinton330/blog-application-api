package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Post;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.PostDTO;
import com.blogapplication.repository.PostRepository;
import com.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDTO createPost(PostDTO postDto) {
        //Request Data
        Post post=mapToEntity(postDto);
        //Posting to Database
        Post newPost=postRepository.save(post);
        //Response Data
        PostDTO postResponse=mapToDTO(post);
        return  postResponse;
    }

    @Override
    public List<PostDTO> getAllPost() {
        List <Post>getAllData=postRepository.findAll();
        List <PostDTO>getEachData=new ArrayList();
        for(Post post:getAllData)
        {
            PostDTO p1= mapToDTO(post);
            getEachData.add(p1);
        }

        return getEachData;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post=postRepository.findById(id).
                                 orElseThrow(()->new ResourceNotFoundException("Post","Id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePostById(PostDTO post1, long id) {
        Post post=postRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Post","Id",id));
        post.setTitle(post1.getTitle());
        post.setDescription(post1.getDescription());
        post.setContent(post1.getContent());
        Post updatePost=postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        Post post=postRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Post","Id",id));
        postRepository.delete(post);
    }


    private PostDTO mapToDTO(Post post)
    {
        PostDTO postDTO=new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());
        return postDTO;
    }

    private Post mapToEntity(PostDTO postDto)
    {
        Post post=new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
