package vehicleRental.models;

public class Booking {

    public Booking(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    //    private Vehicle vehicle;
    private int startTime;
    private int endTime;
}
