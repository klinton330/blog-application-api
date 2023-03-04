package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Catagory;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.CatagoryDTO;
import com.blogapplication.repository.CatagoryRepository;
import com.blogapplication.service.CatagoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CatagoryDTO> getAllCatagory() {
        List<Catagory>catagories=catagoryRepository.findAll();

        return catagories
                .stream()
                .map((catagory)->modelMapper.map(catagory,CatagoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CatagoryDTO updateCatagory(CatagoryDTO catagoryDTO, Long id) {
        //Check catagory is on db
        Catagory catagory=catagoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Catagory","id",id));
        catagory.setName(catagoryDTO.getName());
        catagory.setDescription(catagoryDTO.getDescription());
        Catagory catagory1=catagoryRepository.save(catagory);
        return modelMapper.map(catagory1,CatagoryDTO.class);
    }

    @Override
    public void deleteCatagory(Long Id) {
        Catagory catagory=catagoryRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("Catagory","id",Id));
        catagoryRepository.delete(catagory);
    }


}
