package com.blogapplication.service;

import com.blogapplication.entity.Catagory;
import com.blogapplication.payload.CatagoryDTO;

import java.util.List;

public interface CatagoryService {

    CatagoryDTO addCatagory(CatagoryDTO catagoryDTO);

    CatagoryDTO getCatagory(Long catagoryId);

    List<CatagoryDTO> getAllCatagory();

    CatagoryDTO updateCatagory(CatagoryDTO catagoryDTO,Long id);

    void deleteCatagory(Long Id);

}
