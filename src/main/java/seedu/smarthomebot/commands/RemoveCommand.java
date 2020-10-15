package seedu.smarthomebot.commands;

import seedu.smarthomebot.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.InvalidRemovalLocationException;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_LOCATION_NOT_EXIST;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the indicated LOCATION and all the appliances in that LOCATION.\n"
            + "Parameters: LOCATION \n"
            + "Example: " + COMMAND_WORD
            + " Bedroom 1";
    private final String usersEnteredLocation;

    public RemoveCommand(String location) {
        this.usersEnteredLocation = location;
    }

    @Override
    public CommandResult execute() {
        try {
            locationList.removeLocation(this.usersEnteredLocation);
            applianceList.deleteByLocation(this.usersEnteredLocation);
            return new CommandResult(LINE + "Removing LOCATION \"" + this.usersEnteredLocation + "\"......REMOVED!");
        } catch (InvalidRemovalLocationException e) {
            return new CommandResult(MESSAGE_LOCATION_NOT_EXIST + " Nothing will be deleted.");
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(MESSAGE_APPLIANCE_NOT_EXIST + " Nothing will be deleted.");
        }
    }
}
