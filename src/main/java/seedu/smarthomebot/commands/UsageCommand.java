package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.common.Messages.MESSAGE_POWER_USAGE;

/**
 * Usage command of the application to show power usage.
 */

public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";

    @Override
    public CommandResult execute() {
        double totalUsage = 0;
        int index = 1;

        if (applianceList.getAllAppliance().size() == 0) {
            return new CommandResult(LINE + MESSAGE_LIST_NO_APPLIANCES);
        } else {
            ui.printToUser(LINE + MESSAGE_POWER_USAGE);
            for (Appliance a : applianceList.getAllAppliance()) {
                double appliancePower = a.measureConsumption();
                totalUsage += appliancePower;
                ui.showWithUsageFormat(index, a.getName(), a.getLocation(), a.getStatus(), a.measureConsumption());
                index++;
            }
            String formattedUsage = String.format("%.2f kWh", totalUsage);
            return new CommandResult("\nTotal power consumption: " + formattedUsage);
        }
    }
}
