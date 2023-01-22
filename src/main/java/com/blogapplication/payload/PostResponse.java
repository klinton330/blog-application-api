package com.blogapplication.payload;

import com.blogapplication.entity.Post;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDTO> content;
    private int pageNo;
    private  int pageSize;
    private  long totalElements;
    private int totalPages;
    private boolean last;
}
