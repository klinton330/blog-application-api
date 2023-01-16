package com.blogapplication.payload;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO {

    private long id;
    @NotEmpty
    @Size(min = 2,message = "Post title should have atleast 2 character")
    private String title;
    @NotEmpty
    @Size(min = 10,message = "Post title should have atleast 10 character")
    private String description;
    @NotEmpty
    private String content;

    private Set<CommentDTO>comments;
}
