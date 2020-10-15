package seedu.smarthomebot.commands;

import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.InvalidAddtionOfLocation;

import static seedu.smarthomebot.common.Messages.MESSAGE_LOCATION_EXIST;

public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new location in SmartHomeBot\n"
            + "Parameters: LOCATION\n"
            + "Example: " + COMMAND_WORD
            + " Bedroom 1";
    private final String usersEnteredLocation;
    private final Boolean toPrint;

    public CreateCommand(String location, Boolean toPrint) throws EmptyParameterException {
        this.toPrint = toPrint;
        if (location.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.usersEnteredLocation = location;
    }

    @Override
    public CommandResult execute() {
        try {
            locationList.addLocation(usersEnteredLocation);
            if (this.toPrint) {
                return new CommandResult("Creating Location \"" + usersEnteredLocation + "\".....CREATED!");
            }
        } catch (InvalidAddtionOfLocation e) {
            return new CommandResult(MESSAGE_LOCATION_EXIST);
        }
        return null;
    }
}