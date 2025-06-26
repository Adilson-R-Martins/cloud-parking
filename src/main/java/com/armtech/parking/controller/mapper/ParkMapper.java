package com.armtech.parking.controller.mapper;

import com.armtech.parking.controller.dto.ParkDTO;
import com.armtech.parking.model.Park;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkDTO toParkDTO(Park park) {
        return MODEL_MAPPER.map(park, ParkDTO.class);
    }

    public List<ParkDTO> toParkDTOList(List<Park> parkList) {
        return parkList.stream().map(this::toParkDTO).collect(Collectors.toList());
    }

    public Park toPark(ParkDTO dto) {
        return MODEL_MAPPER.map(dto, Park.class);
    }

    public Park toParkCreate(ParkDTO dto) {
        return MODEL_MAPPER.map(dto, Park.class);
    }
}
