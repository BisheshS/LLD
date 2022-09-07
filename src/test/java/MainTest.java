import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicleRental.Main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void mainTest() throws IOException {
       final String expectedOutput =
               "Created a new Branch B1\n" +
                       "VEHICLE ADDED V1  TO BRANCH B1\n" +
                       "VEHICLE ADDED V2  TO BRANCH B1\n" +
                       "VEHICLE ADDED V3  TO BRANCH B1\n" +
                       "VEHICLE ADDED V4  TO BRANCH B1\n" +
                       "VEHICLE ADDED V5  TO BRANCH B1\n" +
                       "VEHICLE ADDED V6  TO BRANCH B1\n" +
                       "Vehicle Type BUS cannot be registered in branch B1\n" +
                       "ERROR\n" +
                       "Vehicle Type VAN not available in the branch B1\n" +
                       "-1\n" +
                       "ERROR\n" +
                       "Created a booking with B1 of vehicle V4 for time start: 1 end : 3\n" +
                       "CHARGE : 800.0\n" +
                       "Created a booking with B1 of vehicle V5 for time start: 2 end : 3\n" +
                       "CHARGE : 250.0\n" +
                       "Created a booking with B1 of vehicle V6 for time start: 2 end : 5\n" +
                       "CHARGE : 900.0\n" +
                       "Booking not possible : because no free slots for the requested time\n" +
                       "-1\n" +
                       "ERROR\n" +
                       "Created a booking with B1 of vehicle V1 for time start: 1 end : 3\n" +
                       "CHARGE : 1000.0\n" +
                       "Created a booking with B1 of vehicle V2 for time start: 2 end : 4\n" +
                       "CHARGE : 2000.0\n" +
                       "Created a booking with B1 of vehicle V3 for time start: 1 end : 3\n" +
                       "CHARGE : 2640.0\n" +
                       "No vehicles available \n" +
                       "-1\n" +
                       "ERROR\n";
        Main.main(new String[] {"input.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }
}
