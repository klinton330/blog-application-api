package com.blogapplication.controller;

import com.blogapplication.entity.Catagory;
import com.blogapplication.payload.CatagoryDTO;
import com.blogapplication.repository.CatagoryRepository;
import com.blogapplication.service.CatagoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catagory")
@SecurityRequirement(name = "bearerAuth")
public class CatagoryController {

    @Autowired
    private CatagoryService catagoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CatagoryDTO> createCatagory(@RequestBody CatagoryDTO catagoryDTO) {
        CatagoryDTO catagoryDTO1=catagoryService.addCatagory(catagoryDTO);
        return new ResponseEntity<>(catagoryDTO1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CatagoryDTO>getCatagory(@PathVariable("id") Long catagoryId)
    {
        return new ResponseEntity<>(catagoryService.getCatagory(catagoryId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CatagoryDTO>>getCatagories()
    {
        List<CatagoryDTO>catagoryDTOS=catagoryService.getAllCatagory();
        return new ResponseEntity<>(catagoryDTOS,HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CatagoryDTO>updateCatagory(@RequestBody  CatagoryDTO catagoryDTO,@PathVariable("id") Long catagoryId)
    {
        CatagoryDTO catagoryDTO1=catagoryService.updateCatagory(catagoryDTO,catagoryId);
        return new ResponseEntity<>(catagoryDTO1,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>deleteCatagory(@PathVariable("id") Long id)
    {
        catagoryService.deleteCatagory(id);
        return ResponseEntity.ok("Catagory Deleted Successfully");
    }



}
