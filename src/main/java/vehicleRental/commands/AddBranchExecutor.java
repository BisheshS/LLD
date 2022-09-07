package vehicleRental.commands;

import vehicleRental.service.VehicleRentalService;
import vehicleRental.models.Branch;
import vehicleRental.models.Command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddBranchExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "ADD_BRANCH";

    public AddBranchExecutor(final VehicleRentalService vehicleRentalService) {
        super(vehicleRentalService);
    }

    @Override
    public boolean validate(Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 2) {
            return false;
        }
        return vehicleRentalService.validateNewBranch(params);
    }

    @Override
    public void execute(Command command) {
        final List<String> params = command.getParams();
        final String branchName = params.get(0);
        final List<String> vehicleTypeList = Arrays.stream(params.get(1).trim().split(","))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        final Branch branch = new Branch(branchName, vehicleTypeList);
        vehicleRentalService.addBranch(branch);
        System.out.println(
                "Created a new Branch " + branchName);
    }
}
