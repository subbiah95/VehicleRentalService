package com.vehicle.rental;

import com.vehicle.rental.data.CityRepository;
import com.vehicle.rental.data.CalendarRepository;
import com.vehicle.rental.data.BranchRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.VechicleType;
import com.vehicle.rental.model.Vehicle;
import com.vehicle.rental.service.VehicleBookingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VehicleRentalApp {
    private static final String city = "Chennai";
    private static void onBoarding(){
        Branch branch = new Branch("Branch1");
        CityRepository.getInstance().addBranch(city,branch);

        Vehicle vehicle1 = new Vehicle("ABCD123",VechicleType.HATCHBACK, 100);
        Vehicle vehicle2 = new Vehicle("ABCD123",VechicleType.SEDAN, 200);
        Vehicle vehicle3 = new Vehicle("ABCD123",VechicleType.SUV, 300);
        Vehicle vehicle4 = new Vehicle("ABCD123",VechicleType.HATCHBACK, 400);

        BranchRepository instance = BranchRepository.getInstance();
        CalendarRepository calendarInstance = CalendarRepository.getInstance();

        instance.addVehicleToBranch(branch, vehicle1);
        calendarInstance.addVehicle(vehicle1);

        instance.addVehicleToBranch(branch, vehicle2);
        calendarInstance.addVehicle(vehicle2);

        instance.addVehicleToBranch(branch, vehicle3);
        calendarInstance.addVehicle(vehicle3);

        instance.addVehicleToBranch(branch, vehicle4);
        calendarInstance.addVehicle(vehicle4);
    }

    private static Branch selectBranch(){
        Scanner sc = new Scanner(System.in);

        List<Branch> branchList = VehicleBookingService.getInstance().getAllBranchesofCity(city);
        System.out.println(branchList);
        System.out.println("Select a branch");
        Integer option = sc.nextInt();

        return branchList.get(option);
    }

    private static void addNewVehicle(){
        Scanner sc = new Scanner(System.in);

        Branch branch = selectBranch();

        System.out.println("Enter vehicle Details\n" +
                "1.licence No\n" +
                "2.Vehicle Type\n" +
                "3.Vehicle price");
        String licenceNo = sc.nextLine();
        VechicleType vechicleType = VechicleType.getType(sc.nextLine());
        int price = sc.nextInt();
        Vehicle vehicle = new Vehicle(licenceNo, vechicleType, price);
        BranchRepository.getInstance().addVehicleToBranch( branch, vehicle);
        CalendarRepository.getInstance().addVehicle(vehicle);
    }

    private static void displayVehicle(){
        Branch branch = selectBranch();
        System.out.println(VehicleBookingService.getInstance().getAllVehicleOfBranch(branch));
    }

    private static void bookVehicle(){
        Scanner sc = new Scanner(System.in);

        Branch branch = selectBranch();
        System.out.println("Get Slot number");
        int slotNo = sc.nextInt(); sc.nextLine();
        System.out.println("Get Vehicle Type");
        VechicleType vechicleType = VechicleType.getType(sc.nextLine());
        Optional<Vehicle> vehicle = VehicleBookingService.getInstance().bookSlot(branch, vechicleType, slotNo);
        if(vehicle.isPresent())
            System.out.println("Vehicle booked "+ vehicle.get());
        else
            System.out.println("Vehicle Not available");
    }

    public static void main(String[] args) {
        onBoarding();
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while(option != 4){
            System.out.println("Menu\n" +
                    "1.Onboard New Vehicle\n" +
                    "2.Display All Vehicles\n" +
                    "3.Rent a Vehicle\n" +
                    "4.Exit");
            option = sc.nextInt();
            switch(option){
                case 1 :
                    addNewVehicle();
                    break;
                case 2:
                    displayVehicle();
                    break;
                case 3:
                    bookVehicle();
                    break;
            }
        }
        System.out.println("End of App");
    }
}
