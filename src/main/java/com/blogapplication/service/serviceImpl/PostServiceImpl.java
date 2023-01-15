package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Post;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.PostDTO;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.repository.PostRepository;
import com.blogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDto) {
        //Request Data
        Post post = mapToEntity(postDto);
        //Posting to Database
        Post newPost = postRepository.save(post);
        //Response Data
        PostDTO postResponse = mapToDTO(post);
        return postResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageAble = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageAble);
        List<Post> getAllData = posts.getContent();
        List<PostDTO> getEachData = new ArrayList();
        for (Post post : getAllData) {
            PostDTO p1 = mapToDTO(post);
            getEachData.add(p1);
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(getAllData);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePostById(PostDTO post1, long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(post1.getTitle());
        post.setDescription(post1.getDescription());
        post.setContent(post1.getContent());
        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepository.delete(post);
    }


    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO=modelMapper.map(post,PostDTO.class);


       /* PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());*/
        return postDTO;
    }

    private Post mapToEntity(PostDTO postDto) {
        Post post=modelMapper.map(postDto,Post.class);
      /*  Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());*/
        return post;
    }
}
