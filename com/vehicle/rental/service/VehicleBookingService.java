package com.vehicle.rental.service;

import com.vehicle.rental.data.CityRepository;
import com.vehicle.rental.data.CalendarRepository;
import com.vehicle.rental.data.BranchRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.VechicleType;
import com.vehicle.rental.model.Vehicle;

import java.util.*;

public class VehicleBookingService {

    private static long initTime;
    private static long oneHour = 60 * 60 * 1000;
    private static VehicleBookingService instance;

    private VehicleBookingService(){
    }

    public static void initClock(){
        initTime = System.currentTimeMillis();
    }

    public Integer getExpiredSlotCounts(){
        double slot = ((double)System.currentTimeMillis()-initTime)/oneHour;
        slot = Math.floor(slot);
        return (int)slot;
    }

    public static VehicleBookingService getInstance(){
        if(instance == null) instance = new VehicleBookingService();
        return instance;
    }

    public List<Branch> getAllBranchesofCity(String city){
        return CityRepository.getInstance().getBranchesOfCity(city);
    }

    public List<Vehicle> getAllVehicleOfBranch(Branch branch){
        List<Vehicle> vehicleList =  BranchRepository.getInstance().getVehiclesOfBranch(branch);
        Collections.sort(vehicleList, Comparator.comparing(Vehicle::getRentalPrice));
        return vehicleList;
    }

    private List<Vehicle> getAllVehicleOfType(Branch branch, VechicleType vechicleType){
        List<Vehicle> vehicles = new ArrayList<>();
        for(Vehicle vehicle : BranchRepository.getInstance().getVehiclesOfBranch(branch)){
            if( vehicle.getVechicleType() == vechicleType )
                vehicles.add(vehicle);
        }
        Collections.sort(vehicles, Comparator.comparing(Vehicle::getRentalPrice));
        return vehicles;
    }


    public Optional<Vehicle> bookSlot(Branch branch, VechicleType vechicleType, Integer slotNumber){
        if(slotNumber < CalendarRepository.SLOTS_PER_DAY){
            CalendarRepository calendarRepository = CalendarRepository.getInstance();
            for( Vehicle vehicle : getAllVehicleOfType(branch, vechicleType)){
                if(calendarRepository.getVehicleAvailability(vehicle).get(slotNumber)){
                    calendarRepository.getVehicleAvailability(vehicle).set(slotNumber, false);
                    return Optional.of(vehicle);
                }
            }
        }
        return Optional.empty();
    }
}
