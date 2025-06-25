package com.armtech.parking.controller;

import com.armtech.parking.controller.dto.ParkDTO;
import com.armtech.parking.controller.mapper.ParkMapper;
import com.armtech.parking.model.Park;
import com.armtech.parking.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkController {


    private final ParkService parkService;

    private final ParkMapper parkMapper;

    public ParkController(ParkService parkService, ParkMapper parkMapper) {
        this.parkService = parkService;
        this.parkMapper = parkMapper;
    }


    @GetMapping
    List<ParkDTO> findAll(){
         List<Park> parkList = parkService.findAll();
         List<ParkDTO> result = parkMapper.toParkDTOList(parkList);
         return result;
    };


}
