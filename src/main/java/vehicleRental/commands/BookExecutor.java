package vehicleRental.commands;

import vehicleRental.service.VehicleRentalService;
import vehicleRental.models.*;

import java.util.List;

public class BookExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "BOOK";


    public BookExecutor(VehicleRentalService vehicleRentalService) {
        super(vehicleRentalService);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        if(params.size()!=4) return false;
        return vehicleRentalService.validateBooking(params);
    }

    @Override
    public void execute(Command command) {
        vehicleRentalService.createVehicleBooking(command.getParams());
    }
}
