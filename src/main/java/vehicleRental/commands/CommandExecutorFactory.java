package vehicleRental.commands;

import vehicleRental.service.VehicleRentalService;
import vehicleRental.models.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {

    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(final VehicleRentalService vehicleRentalService){

        commands.put(AddBranchExecutor.COMMAND_NAME, new AddBranchExecutor(vehicleRentalService));
        commands.put(AddVehicleExecutor.COMMAND_NAME, new AddVehicleExecutor(vehicleRentalService));
        commands.put(BookExecutor.COMMAND_NAME, new BookExecutor(vehicleRentalService));
        commands.put(DisplayExecutor.COMMAND_NAME, new DisplayExecutor(vehicleRentalService));

    }

    public CommandExecutor getCommandExecutor(final Command command) {
        final CommandExecutor commandExecutor = commands.get(command.getCommandName());
        if (commandExecutor == null) {
            System.out.println("ISSUE");
        }
        return commandExecutor;
    }
}
