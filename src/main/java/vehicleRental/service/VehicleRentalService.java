package vehicleRental.service;

import vehicleRental.models.Branch;
import vehicleRental.models.Vehicle;
import vehicleRental.models.VehicleType;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleRentalService {

    public VehicleRentalService(){
        branches = new ArrayList<>();
    }
    private List<Branch> branches;

    public List<Branch> getBranches(){
        return branches;
    }
    public void addBranch(final Branch branch){
        branches.add(branch);
    }

    public boolean validateNewBranch(List<String> params) {
        if(validateBranchExists(params.get(0))){
            throw new RuntimeException("Branch already exists, cannot add new Branch");
        }
        final List<String> vehicleList = Arrays.stream(params.get(1).trim().split(","))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        boolean vehicleTypeExists = false;
        for (String vehicle: vehicleList) {
            if (Arrays.stream(VehicleType.values()).anyMatch((t)->t.name().equalsIgnoreCase(vehicle)))
                vehicleTypeExists = true;
            else
                return false;
        }
        if (!vehicleTypeExists)
            return false;
        return true;
    }

    public void addNewVehicleToBranch(List<String> params){
        String branchName = params.get(0);
        String vehicleType = params.get(1);
        VehicleType vehicleTypeEnum = VehicleType.valueOf(vehicleType);
        String vehicleName = params.get(2);
        Double cost = Double.valueOf(params.get(3));
        Vehicle vehicle = new Vehicle(vehicleName,vehicleTypeEnum, cost);

        for(Branch branch : branches){
            if(branch.getBranchName().equalsIgnoreCase(branchName)){
                branch.addVehicle(vehicle);
                System.out.println("VEHICLE ADDED " + vehicleName + " " + " TO BRANCH " + branchName);
            }
        }
    }

    public boolean validateNewVehicle(List<String> params){
        String branchName = params.get(0);
        if (!validateBranchExists(branchName)){
            System.out.println("No Branch exists with name " + branchName);
            return false;
        }
        for (Branch branch : branches) {
            if (branch.getBranchName().equalsIgnoreCase(params.get(0))) {
                if(!branch.validateNewVehicle(params.get(1))){
                    System.out.println("Vehicle Type " + params.get(1) + " cannot be registered in branch " +
                            params.get(0));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateBranchExists(String branchName){
        for (Branch branch: branches){
            if (branch.getBranchName().equalsIgnoreCase(branchName)){
                return true;
            }
        }
        return false;
    }


    public boolean validateBooking(List<String> params){
        String branchName = params.get(0);
        String vehicleType = params.get(1);
        VehicleType vehicleTypeEnum = VehicleType.valueOf(vehicleType);
        Integer startTime = Integer.valueOf(params.get(2));
        Integer endTime = Integer.valueOf(params.get(3));

        if (!validateBranchExists(branchName)){
            System.out.println("No Branch exists with name " + branchName);
            return false;
        }

        for (Branch branch : branches) {
            if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                if (branch.validVehicleTypeExists(vehicleTypeEnum)){
                    if(!branch.validateVehicleBooking(vehicleTypeEnum, startTime, endTime)){
                        System.out.println("Booking not possible : because no free slots for the requested time");
                        System.out.println("-1");
                        return false;
                    }
                }
                else{
                    System.out.println("Vehicle Type " + vehicleType + " not available in the branch " +
                            branchName);
                    System.out.println("-1");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateDisplayVehicles(List<String> params){
        String branchName = params.get(0);

        Integer startTime = Integer.valueOf(params.get(1));
        Integer endTime = Integer.valueOf(params.get(2));

        if (!validateBranchExists(branchName)){
            System.out.println("No Branch exists with name " + branchName);
            return false;
        }
        for (Branch branch : branches) {
            if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                if(!branch.validateDisplayVehicles(startTime, endTime)){
                    System.out.println("No vehicles available ");
                    System.out.println("-1");
                    return false;
                }
            }
        }
        return true;
    }

    public void displayAvailableVehicles(List<String> params){
        String branchName = params.get(0);

        Integer startTime = Integer.valueOf(params.get(1));
        Integer endTime = Integer.valueOf(params.get(2));
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Branch branch : branches) {
            if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                branch.displayAvailableVehicles(availableVehicles, startTime, endTime);
            }
        }
        for (Vehicle vehicle : availableVehicles){
            System.out.println("Available : " + vehicle.getId());
        }
    }

    public void createVehicleBooking(List<String> params){
        String branchName = params.get(0);
        String vehicleType = params.get(1);
        VehicleType vehicleTypeEnum = VehicleType.valueOf(vehicleType);

        Integer startTime = Integer.valueOf(params.get(2));
        Integer endTime = Integer.valueOf(params.get(3));

        for (Branch branch : branches) {
            if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                branch.createVehicleBooking(vehicleTypeEnum, startTime, endTime);
            }
        }
    }
}
