package com.blogapplication.repository;

import com.blogapplication.entity.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatagoryRepository extends JpaRepository<Catagory,Long> {
}
