package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.smarthomebot.data.LocationList;
import seedu.smarthomebot.exceptions.DuplicateDataException;
import seedu.smarthomebot.exceptions.InvalidRemovalLocationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveTest {

    @Test
    public void removeLocationTest() throws DuplicateDataException {
        //Create Sample Locations and empty appliance list
        LocationList locationList = new LocationList();
        locationList.addLocation("BedRoom1");
        locationList.addLocation("BedRoom3");

        // Prepare to read output of command
        assertThrows(InvalidRemovalLocationException.class, () -> locationList.removeLocation("Bedroom 2"));
    }
}