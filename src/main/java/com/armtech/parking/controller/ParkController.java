package com.armtech.parking.controller;

import com.armtech.parking.controller.dto.ParkDTO;
import com.armtech.parking.controller.mapper.ParkMapper;
import com.armtech.parking.model.Park;
import com.armtech.parking.service.ParkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking Management", description = "Related operations for parking slots.")
public class ParkController {


    private final ParkService parkService;

    private final ParkMapper parkMapper;

    public ParkController(ParkService parkService, ParkMapper parkMapper) {
        this.parkService = parkService;
        this.parkMapper = parkMapper;
    }


    @GetMapping
    @Operation(
            summary = "Find All",
            description = "Retrieve all parked vehicles."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicles successfully found")
    })
    ResponseEntity<List<ParkDTO>> findAll() {
        List<Park> parkList = parkService.findAll();
        List<ParkDTO> result = parkMapper.toParkDTOList(parkList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Search by ID",
            description = "Retrieve parked vehicle parking data by ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ID successfully found"),
            @ApiResponse(responseCode = "404", description = "ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<ParkDTO> findById(@Parameter(description = "Entry ID", required = true) @PathVariable String id) {
        Park park = parkService.finBydId(id);
        ParkDTO result = parkMapper.toParkDTO(park);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(
            summary = "Create",
            description = "Create a new vehicle entry on the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New entry successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid data input")
    })
    ResponseEntity<ParkDTO> create(@Parameter(description = "Vehicle details") @RequestBody ParkDTO parkCreateDTO) {
        Park body = parkMapper.toParkCreate(parkCreateDTO);
        Park park = parkService.create(body);
        ParkDTO result = parkMapper.toParkDTO(park);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete",
            description = "Delete a vehicle entry on the system."
    )
    ResponseEntity delete(@Parameter(description = "Entry ID", required = true) @PathVariable String id) {
        parkService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update",
            description = "Update a vehicle entry on the system."
    )
    ResponseEntity<ParkDTO> update(@PathVariable String id, @RequestBody ParkDTO parkCreateDTO) {
        Park body = parkMapper.toParkCreate(parkCreateDTO);
        Park park = parkService.update(id, body);
        ParkDTO result = parkMapper.toParkDTO(park);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}")
    @Operation(
            summary = "CheckOut",
            description = "Enter the exit time and display the parking fee."
    )
    public ResponseEntity<ParkDTO> checkOut(@PathVariable String id) {
        Park park = parkService.checkOut(id);
        return ResponseEntity.ok(parkMapper.toParkDTO(park));
    }

}
