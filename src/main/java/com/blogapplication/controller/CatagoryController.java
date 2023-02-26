package com.blogapplication.controller;

import com.blogapplication.payload.CatagoryDTO;
import com.blogapplication.service.CatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catagory")
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

}
