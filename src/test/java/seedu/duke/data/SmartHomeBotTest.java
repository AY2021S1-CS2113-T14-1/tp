package seedu.duke.data;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.common.Messages.MESSAGE_LOCATION_NOT_EXIST;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.exceptions.InvalidAdditionOfAppliance;
import seedu.duke.exceptions.InvalidAddtionOfLocation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class SmartHomeBotTest {

    @Test
    void getNameTest() {
        assertEquals("Xiao Mi Light", new Lights("Xiao Mi Light", "BedRoom 1", "45").getName());
    }

    @Test
    void getLocationTest() {
        assertEquals("BedRoom1", new Lights("Xiao Mi Light", "BedRoom1", "45").getLocation());
    }

    @Test
    void getStatusTest() {
        //New appliance added should be always remain Off until User switch it On
        assertEquals("Off", new Lights("Xiao Mi Light", "BedRoom 1", "45").getStatus());
    }

    @Test
    public void addLocationTest() throws InvalidAddtionOfLocation {
        String br1 = "BedRoom 1";
        String br2 = "BedRoom 2";
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation(br1);
        homeLocations.addLocation(br2);
        assertEquals("[BedRoom 1, BedRoom 2]", homeLocations.getLocations().toString());
        assertThrows(InvalidAddtionOfLocation.class, () -> homeLocations.addLocation(br1));
    }

    @Test
    public void addApplianceTest() throws InvalidAdditionOfAppliance {
        ApplianceList applianceList = new ApplianceList();
        Lights l1 = new Lights("l1", "BedRoom1", "50");
        Lights l2 = new Lights("l2", "BedRoom1", "50");
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);
        assertThrows(InvalidAdditionOfAppliance.class, () -> applianceList.addAppliance(l1));

    }

    @Test
    void getTypeTest() {
        assertEquals("AirConditioner", new AirConditioner("aircon1", "br1", "25").getType());
    }

    @Test
    void onOffTest() {
        AirConditioner aircon = new AirConditioner("aircon", "br1", "200");
        if (aircon.getStatus().equals("On")) {
            assertEquals("aircon: On", aircon.toString());
        } else {
            assertEquals("aircon: Off", aircon.toString());
        }
    }

    @Test
    public void removeLocationTest() throws InvalidAddtionOfLocation, EmptyParameterException {
        //Create Sample Locations and empty appliance list
        HomeLocations homeLocations = new HomeLocations();
        homeLocations.addLocation("BedRoom1");
        homeLocations.addLocation("BedRoom3");
        // Prepare to read output of command
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ApplianceList applianceList = new ApplianceList();
        // Remove Invalid location
        Command removeLocation = new RemoveCommand("Bedroom2");
        removeLocation.setData(applianceList, homeLocations);
        removeLocation.execute();

        //Process ui output
        String outputString = outContent.toString().replace(System.getProperty("line.separator"), "");

        //compare outputs
        assertEquals(MESSAGE_LOCATION_NOT_EXIST + " Nothing will be deleted.", outputString);
    }

}
