package vehicleRental.service;

import vehicleRental.models.Branch;
import vehicleRental.models.Vehicle;

public class BranchService {

    private Branch branch;

    public BranchService(Branch branch){
        this.branch = branch;
    }

    public void addVehicle(Vehicle vehicle){
        branch.addVehicle(vehicle);
    }



}
