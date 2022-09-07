package vehicleRental.commands;

import vehicleRental.service.VehicleRentalService;
import vehicleRental.models.*;


import java.util.List;

public class DisplayExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "DISPLAY_VEHICLES";

    public DisplayExecutor(VehicleRentalService vehicleRentalService) {
        super(vehicleRentalService);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        if(params.size()!=3) return false;
        return vehicleRentalService.validateDisplayVehicles(params);
    }

    @Override
    public void execute(Command command) {
        List<String> params = command.getParams();
        vehicleRentalService.displayAvailableVehicles(params);
    }
}
