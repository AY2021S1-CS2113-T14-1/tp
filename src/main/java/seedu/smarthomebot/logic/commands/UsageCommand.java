package seedu.smarthomebot.logic.commands;

import seedu.smarthomebot.model.framework.Appliance;

import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_POWER_USAGE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_TOTAL_POWER_USAGE;

/**
 * Usage command of the application to show power usage.
 */

public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";
    public static final String MESSAGE_USAGE = "Usage of Appliance: " + COMMAND_WORD;
    public static final String DISPLAY_LOCATION = " | Location: ";
    public static final String DISPLAY_STATUS = " | Status: ";
    public static final String DISPLAY_USAGE = " | Usage: ";

    @Override
    public CommandResult execute() {
        double totalUsage = 0;
        int index = 1;

        if (applianceList.getAllAppliance().size() == 0) {
            return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
        } else {
            String formattedResult = (LINE + MESSAGE_POWER_USAGE);
            String format = "%-2d. %-" + Appliance.getMaxNameLength() + "s"
                    + DISPLAY_LOCATION + "%-" + Appliance.getMaxLocationLength() + "s"
                    + DISPLAY_STATUS + "%-5s"
                    + DISPLAY_USAGE + "%.2f kWh";
            for (Appliance a : applianceList.getAllAppliance()) {
                formattedResult = formattedResult.concat(System.lineSeparator() + String.format(format, index,
                        a.getName(), a.getLocation(), a.getStatus(), a.getPowerInDouble()));
                totalUsage += a.getPowerInDouble();
                index++;
            }
            formattedResult = formattedResult.concat(MESSAGE_TOTAL_POWER_USAGE + String.format("%.2f kWh", totalUsage));
            return new CommandResult(formattedResult);
        }
    }

}
