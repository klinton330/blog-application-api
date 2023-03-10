package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Catagory;
import com.blogapplication.entity.Post;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.PostDTO;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.repository.CatagoryRepository;
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
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDto) {
       Catagory catagory= catagoryRepository.findById(postDto.getCatagoryId())
               .orElseThrow(()->new ResourceNotFoundException("catagory Id Not found","id",postDto.getCatagoryId()));
        //Request Data
        Post post = mapToEntity(postDto);
        post.setCatagory(catagory);
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
        System.out.println(getAllData);
        List<PostDTO> getEachData = new ArrayList();
        for (Post post : getAllData) {
            PostDTO p1 = mapToDTO(post);
            getEachData.add(p1);
        }
        System.out.println(getEachData);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(getEachData);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        System.out.println(postResponse);
        return postResponse;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        System.out.println("Thepost"+post);
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePostById(PostDTO post1, long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        Catagory catagory= catagoryRepository.findById(post1.getCatagoryId())
                .orElseThrow(()->new ResourceNotFoundException("catagory Id Not found","id",post1.getCatagoryId()));
        post.setTitle(post1.getTitle());
        post.setDescription(post1.getDescription());
        post.setContent(post1.getContent());
        post.setCatagory(catagory);
        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getPostByCatagory(Long catagoryId) {
        catagoryRepository.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory Not Found","Id",catagoryId));
        List<Post>posts=postRepository.findByCatagoryId(catagoryId);
        return posts.stream().map(x->mapToDTO(x)).collect(Collectors.toList());
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
