package com.armtech.parking.repository;

import com.armtech.parking.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Park, String> {
}
