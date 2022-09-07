package vehicleRental;

import vehicleRental.commands.CommandExecutor;
import vehicleRental.commands.CommandExecutorFactory;
import vehicleRental.models.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

    private String fileName;

    private CommandExecutorFactory commandExecutorFactory;

    public FileReader(
            final CommandExecutorFactory commandExecutorFactory,
            final String fileName) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.fileName = fileName;
    }

    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new java.io.FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");;
            return;
        }

        String input = reader.readLine();
        while (input != null) {
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }

    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            System.out.println("ERROR");
        }
    }
}
