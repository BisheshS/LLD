package vehicleRental.commands;


import vehicleRental.service.VehicleRentalService;
import vehicleRental.models.Command;

public abstract class CommandExecutor {
    protected VehicleRentalService vehicleRentalService;

    public CommandExecutor(final VehicleRentalService vehicleRentalService){
        this.vehicleRentalService = vehicleRentalService;
    }

    public abstract boolean validate(Command command);

    public abstract void execute(Command command);
}
