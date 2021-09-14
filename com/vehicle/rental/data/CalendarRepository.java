package com.vehicle.rental.data;

import com.vehicle.rental.model.Vehicle;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CalendarRepository {
    private Map<Vehicle, List<Boolean>> vehicleAvailabilityMap;
    public static CalendarRepository instance;
    public static final Integer SLOTS_PER_DAY = 24;

    private CalendarRepository(){
        vehicleAvailabilityMap = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicleAvailabilityMap.putIfAbsent(vehicle, new ArrayList<>());
        for(int i = 0 ; i < SLOTS_PER_DAY ; i++) vehicleAvailabilityMap.get(vehicle).add(true);
    }

    public static CalendarRepository getInstance(){
        if(instance == null) instance = new CalendarRepository();
        return instance;
    }

    public List<Boolean> getVehicleAvailability(Vehicle vehicle){
        return vehicleAvailabilityMap.get(vehicle);
    }
}
