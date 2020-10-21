package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;
import seedu.smarthomebot.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.exceptions.EmptyParameterException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete Appliance: " + COMMAND_WORD
            + " [APPLIANCE_NAME]";
    private final String userEnteredName;

    public DeleteCommand(String name) {
        this.userEnteredName = name;
    }

    @Override
    public CommandResult execute() {
        try {
            Appliance toDeleteAppliance = applianceList.removeAppliance(this.userEnteredName);
            return new CommandResult("Deleting " + toDeleteAppliance + ".......DELETED.");
        } catch (ApplianceNotFoundException e) {
            return new CommandResult(this.userEnteredName + " does not exist.");
        }
    }
}
