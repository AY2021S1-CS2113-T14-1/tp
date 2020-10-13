package seedu.duke.commands;

import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;
import static seedu.duke.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;

public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns off specified appliance by its indicated NAME \n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " Fan 1";
    private final String name;

    public OffCommand(String name) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.name = name;
    }

    @Override
    public void execute() {
        for (Appliance i : appliances.getAllAppliance()) {
            String location = i.getLocation();
            if (i.getName().equals((this.name))) {
                if (i.switchOff()) {
                    String result = String.format("Switching off %s in %s ......OFF!\n", name, location);
                    ui.showToUser(LINE + result);
                } else {
                    ui.showToUser(LINE + MESSAGE_APPLIANCE_PREVIOUSLY_OFF);
                }
                return;
            }
        }
        ui.showToUser(name + " does not exist.");
    }

}
