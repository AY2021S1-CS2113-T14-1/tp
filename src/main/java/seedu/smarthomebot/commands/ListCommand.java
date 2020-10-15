package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_APPLIANCES;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_LOCATIONS;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_LOCATIONS;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display all the appliances that have been added to SmartHomeBot \n"
            + "Example: " + COMMAND_WORD;
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String parameter;

    public ListCommand(String arguments) {
        parameter = arguments;
    }

    @Override
    public CommandResult execute() {
        int index = 1;
        if (parameter.equals(LOCATION_TYPE)) {
            if (locationList.getLocations().size() == 0) {
                return new CommandResult(LINE + MESSAGE_LIST_NO_LOCATIONS);
            }
            ui.printToUser(LINE + MESSAGE_LIST_LOCATIONS);
            for (String location : locationList.getLocations()) {
                ui.printToUser(index + ": " + location);
                index++;
            }
            return new CommandResult("LOCATION STRING");
        } else if (parameter.equals(APPLIANCE_TYPE)) {
            if (applianceList.getAllAppliance().size() == 0) {
                return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
            }
            ui.printToUser(LINE + MESSAGE_LIST_APPLIANCES);
            for (Appliance a : applianceList.getAllAppliance()) {
                ui.showWithListFormat(index, a.getName(), a.getLocation(), a.getStatus(), a.getPower(), a.getType());
                index++;
            }

            return new CommandResult("YOUR STRING");
        } else {
            return new CommandResult("");
        }
    }
}
