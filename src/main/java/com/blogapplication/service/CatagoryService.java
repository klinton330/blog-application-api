package com.blogapplication.service;

import com.blogapplication.entity.Catagory;
import com.blogapplication.payload.CatagoryDTO;

public interface CatagoryService {

    CatagoryDTO addCatagory(CatagoryDTO catagoryDTO);

    CatagoryDTO getCatagory(Long catagoryId);

}
