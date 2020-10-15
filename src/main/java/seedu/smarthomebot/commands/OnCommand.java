package seedu.smarthomebot.commands;

import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_ON;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on specified appliance by its indicated NAME \n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " Aircon 1 ";
    private final String name;

    public OnCommand(String name) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        // NEED TO IMPROVE
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.name))) {
                if (appliance.switchOn()) {
                    String location = appliance.getLocation();
                    String result = String.format("Switching on %s in %s ......ON!\n", name, location);
                    return new CommandResult(LINE + result);
                } else {
                    return new CommandResult(LINE + MESSAGE_APPLIANCE_PREVIOUSLY_ON);
                }
            }
        }
        return new CommandResult(LINE + name + " does not exist.");
    }
}
