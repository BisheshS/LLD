package vehicleRental.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Branch {

    public String getBranchName() {
        return branchName;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    private List<VehicleType> vehicleTypes;
    private String branchName;

    public Branch(String name, List<String> vehicleTypes){
        branchName = name;
        vehicles = new ArrayList<>();
        this.vehicleTypes = new ArrayList<>();
        for (String vehicle : vehicleTypes) {
            VehicleType vehicleType = VehicleType.valueOf(vehicle);
            this.vehicleTypes.add(vehicleType);
        }
    }

    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    private List<Vehicle> vehicles;

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public boolean validateNewVehicle(String newVehicleType){
        for (VehicleType vehicleType : vehicleTypes) {
            if (vehicleType.name().equalsIgnoreCase(newVehicleType)) {
                return true;
            }
        }
        return false;
    }

    public boolean validVehicleTypeExists(VehicleType bookingVehicleType){
        for (Vehicle vehicle : vehicles){
            if (vehicle.getVehicleType().equals(bookingVehicleType)){
                return true;
            }
        }
        return false;
    }

    public boolean validateVehicleBooking(VehicleType bookingVehicleType, Integer startTime, Integer endTime){
        boolean bookingPossible = false;
        for (Vehicle vehicle : vehicles){
            if (vehicle.getVehicleType().equals(bookingVehicleType)){
                if(vehicle.checkFreeSlot(startTime,endTime)){
                    bookingPossible = true;
                }
            }
        }
        return bookingPossible;
    }

    public void createVehicleBooking(VehicleType vehicleTypeEnum, Integer startTime, Integer endTime){
        sortVehiclesAccordingToPrice(vehicles);
        surgePricing(vehicles);
        for (Vehicle vehicle : vehicles){
            if (vehicle.getVehicleType().equals(vehicleTypeEnum)){
                if (vehicle.checkFreeSlot(startTime,endTime)){
                    vehicle.addBooking(new Booking(startTime, endTime));
                    System.out.println(
                            "Created a booking with " + branchName + " of vehicle "
                                    + vehicle.getId() + " for time start: " + startTime + " end : " + endTime);
                    System.out.println("CHARGE : " + vehicle.getPrice()*(endTime-startTime));
                    return;
                }
            }
        }
    }

    private void surgePricing(List<Vehicle> vehicles) {
        int totalBookingsInBranch = 0;
        for (Vehicle vehicle : vehicles){
            if (!vehicle.getBookings().isEmpty()){
                totalBookingsInBranch++;
            }
        }
        if (totalBookingsInBranch>=(0.8)*vehicles.size()){
            for (Vehicle vehicle : vehicles){
                vehicle.setPrice((1.1)*vehicle.getPrice());
            }
        }
    }

    private void sortVehiclesAccordingToPrice(List<Vehicle> vehicles) {
        Collections.sort(vehicles, new Comparator<Vehicle>(){

            public int compare(Vehicle o1, Vehicle o2)
            {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
    }

    public boolean validateDisplayVehicles(Integer startTime, Integer endTime){
        boolean vehicleAvailable = false;
        for (Vehicle vehicle : vehicles){
            if(vehicle.checkFreeSlot(startTime,endTime)){
                vehicleAvailable=true;
            }
        }
        return vehicleAvailable;
    }

    public void displayAvailableVehicles(List<Vehicle> availableVehicles, Integer startTime, Integer endTime){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.checkFreeSlot(startTime,endTime)){
                availableVehicles.add(vehicle);
            }
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }


}
