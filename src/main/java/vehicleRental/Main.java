package vehicleRental;
import vehicleRental.commands.CommandExecutorFactory;
import vehicleRental.service.VehicleRentalService;

import java.io.IOException;

public class Main {

    public static void main(final String args[]) throws IOException {
        final VehicleRentalService vehicleRentalService = new VehicleRentalService();
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(vehicleRentalService);
        new FileReader(commandExecutorFactory, args[0]).process();
    }
}
