package com.armtech.parking.service;

import com.armtech.parking.model.Park;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkService {

    private static Map<String, Park> parkMap = new HashMap();

    static {
        var id = getUUID();
        Park park = new Park((String) id, "DMS-1111", "SC", "CELTA", "PRETO");
        parkMap.put((String) id, park);
    }

    public List<Park> findAll() {
        return parkMap.values().stream().collect(Collectors.toList());
    }

    private static Object getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
