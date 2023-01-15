package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Comment;
import com.blogapplication.entity.Post;
import com.blogapplication.exception.BlogAPIException;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.CommentDTO;
import com.blogapplication.repository.CommentRepository;
import com.blogapplication.repository.PostRepository;
import com.blogapplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PostRepository postRepository;
    @Autowired
    public CommentRepository commentRepository;
    @Override
    public CommentDTO createComment(long id, CommentDTO commentDTO) {
        Comment comment = mapToEntity(commentDTO);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", id));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getAllPost(long postId) {
        List<Comment>getAllPost=commentRepository.findByPostId(postId);
        return getAllPost.stream().map(comment->mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("POST", "ID", postId));
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentId));
        if(comment.getPost().getId()!=post.getId())
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment is not belong to this blog");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("POST", "ID", postId));
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentId));
        if(comment.getPost().getId()!=post.getId())
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment is not belong to this blog");
        }
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        Comment updateComment=commentRepository.save(comment);
        return mapToDTO(updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("POST", "ID", postId));
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","ID",commentId));
        if(comment.getPost().getId()!=post.getId())
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment is not belong to this blog");
        }
        commentRepository.deleteById(commentId);

    }

    public CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDTO=modelMapper.map(comment,CommentDTO.class);
        /*CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setBody(comment.getBody());
        commentDTO.setEmail(comment.getEmail());*/
        return commentDTO;
    }

    public Comment mapToEntity(CommentDTO commentDTO) {
        Comment comment=modelMapper.map(commentDTO,Comment.class);
      /*  Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());*/
        return comment;
    }

}
