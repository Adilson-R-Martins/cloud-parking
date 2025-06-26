package com.armtech.parking.service;

import com.armtech.parking.exception.ParkNotFoundException;
import com.armtech.parking.model.Park;
import com.armtech.parking.repository.ParkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkService {

    private final ParkRepository parkRepository;

    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Park> findAll() {
        return parkRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Park finBydId(String id) {
        return parkRepository.findById(id).orElseThrow(() ->
                new ParkNotFoundException(id));
    }

    @Transactional
    public Park create(Park park) {
        String uuid = getUUID();
        park.setId(uuid);
        park.setEntryDate(LocalDateTime.now());
        parkRepository.save(park);
        return park;
    }

    @Transactional
    public void delete(String id) {
        finBydId(id);
        parkRepository.deleteById(id);
    }

    @Transactional
    public Park update(String id, Park body) {
        Park park = finBydId(id);
        park.setLicense(body.getLicense());
        park.setState(body.getState());
        park.setModel(body.getModel());
        park.setColor(body.getColor());
        parkRepository.save(park);
        return park;
    }

    @Transactional
    public Park checkOut(String id) {
        Park park = finBydId(id);
        park.setExitDate(LocalDateTime.now());
        park.setBill(ParkCheckout.getBill(park));
        parkRepository.save(park);
        return park;
    }
}
