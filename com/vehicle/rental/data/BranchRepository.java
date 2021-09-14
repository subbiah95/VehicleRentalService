package com.vehicle.rental.data;

import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;
import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchRepository {
    private Map<Branch, List<Vehicle>> vehicleMap;
    private static BranchRepository instance;

    private BranchRepository(){
        vehicleMap = new HashMap<>();
    }

    public static BranchRepository getInstance(){
        if(instance == null) instance = new BranchRepository();
        return instance;
    }

    public void addVehicleToBranch(Branch branch, Vehicle vehicle){
        vehicleMap.putIfAbsent(branch, new ArrayList<>());
        vehicleMap.get(branch).add(vehicle);
    }

    public List<Vehicle> getVehiclesOfBranch(Branch branch){
        return vehicleMap.getOrDefault(branch, new ArrayList<>());
    }
}
