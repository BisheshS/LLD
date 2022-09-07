package vehicleRental.models;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    public String getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public boolean checkFreeSlot(Integer startTime, Integer endTime){
        if (bookings.isEmpty()){
            return true;
        }
        boolean bookingPossible = false;
        for (Booking booking: bookings){
            if (booking.getEndTime() < startTime){
                bookingPossible = true;
            }
            else {
                if (booking.getStartTime() > endTime)
                    bookingPossible=true;
                else
                    bookingPossible=false;
            }
        }
        return bookingPossible;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double newPrice) {
        price = newPrice;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    private final String id;
    private final VehicleType vehicleType;
    private Double price;
    private final List<Booking> bookings;

    public Vehicle(String id ,VehicleType vehicleType, Double price){
        this.id = id;
        this.vehicleType = vehicleType;
        this.price = price;
        this.bookings = new ArrayList<>();
    }


}
