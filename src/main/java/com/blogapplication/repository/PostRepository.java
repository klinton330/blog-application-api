package com.blogapplication.repository;

import com.blogapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    //This method will give List of post under one catagory
    List<Post>findByCatagoryId(Long catagoryId);
}
