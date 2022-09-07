package vehicleRental.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private String commandName;

    private List<String> params;

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(final String input){
        final List<String> tokensList = Arrays.stream(input.trim().split(" "))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if (tokensList.size() == 0) {
            System.out.println( "ISSUE");
        }

        commandName = tokensList.get(0);
        tokensList.remove(0);
        params = tokensList;
    }

}
