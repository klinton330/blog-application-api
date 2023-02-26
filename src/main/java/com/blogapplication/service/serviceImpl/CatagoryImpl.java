package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Catagory;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.CatagoryDTO;
import com.blogapplication.repository.CatagoryRepository;
import com.blogapplication.service.CatagoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatagoryImpl implements  CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CatagoryDTO addCatagory(CatagoryDTO catagoryDTO) {
        //ModelMapper converts CatagoryDTO to Catagory Entity
        Catagory catagory=modelMapper.map(catagoryDTO,Catagory.class);
        Catagory catagoryDTO1=catagoryRepository.save(catagory);
        return modelMapper.map(catagoryDTO1,CatagoryDTO.class);
    }

    @Override
    public CatagoryDTO getCatagory(Long catagoryId) {
        Catagory catagory=catagoryRepository
                .findById(catagoryId)
                .orElseThrow(()->new ResourceNotFoundException("Catagory","id",catagoryId));
        return modelMapper.map(catagory,CatagoryDTO.class);
    }
}
