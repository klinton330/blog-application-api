package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Comment;
import com.blogapplication.entity.Post;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.CommentDTO;
import com.blogapplication.repository.CommentRepository;
import com.blogapplication.repository.PostRepository;
import com.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO createComment(long id, CommentDTO commentDTO) {
        Comment comment = mapToEntity(commentDTO);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("POST", "ID", id));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    public CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setBody(comment.getBody());
        commentDTO.setEmail(comment.getEmail());
        return commentDTO;
    }

    public Comment mapToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        return comment;
    }

}
