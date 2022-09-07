package vehicleRental.commands;

import vehicleRental.service.VehicleRentalService;
import vehicleRental.models.Command;

import java.util.List;

public class AddVehicleExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "ADD_VEHICLE";

    public AddVehicleExecutor(final VehicleRentalService vehicleRentalService) {
        super(vehicleRentalService);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        if(params.size()!=4) return false;
        return vehicleRentalService.validateNewVehicle(params);
    }

    @Override
    public void execute(Command command) {
        List<String> params = command.getParams();
        vehicleRentalService.addNewVehicleToBranch(params);
    }
}
