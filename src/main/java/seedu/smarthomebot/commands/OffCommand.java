package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.framework.Appliance;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_PREVIOUSLY_OFF;
import static seedu.smarthomebot.common.Messages.LINE;

public class OffCommand extends Command {

    public static final String COMMAND_WORD = "off";
    public static final String MESSAGE_USAGE = "Switch OFF Appliance: \n\t\t a. " + COMMAND_WORD
            + " [APPLIANCE_NAME] \n\t\t b. " + COMMAND_WORD + " [LOCATION_NAME]";
    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";
    private final String name;

    public OffCommand(String name) {
        this.name = name;
    }

    private int getApplianceToOffIndex() {
        for (Appliance appliance : applianceList.getAllAppliance()) {
            if (appliance.getName().equals((this.name))) {
                return applianceList.getAllAppliance().indexOf(appliance);
            }
        }
        return -1;
    }

    @Override
    public CommandResult execute() {
        String type = APPLIANCE_TYPE;
        ArrayList<Appliance> filterApplianceList =
                (ArrayList<Appliance>) applianceList.getAllAppliance().stream()
                        .filter((s) -> s.getLocation().equals(this.name))
                        .collect(toList());
        if (!filterApplianceList.isEmpty()) {
            type = LOCATION_TYPE;
        }
        switch (type) {
        case (APPLIANCE_TYPE):
            return offByAppliance();
        case (LOCATION_TYPE):
            return offByLocation();
        default:
            return new CommandResult("Invalid Format");
        }
    }

    private CommandResult offByAppliance() {
        int toOffApplianceIndex = getApplianceToOffIndex();
        if (toOffApplianceIndex < 0) {
            return new CommandResult(MESSAGE_APPLIANCE_NOT_EXIST);
        } else {
            Appliance toOffAppliance = applianceList.getAppliance(toOffApplianceIndex);
            String outputResult = offAppliance(toOffAppliance, "", false);
            return new CommandResult(outputResult);
        }
    }

    private CommandResult offByLocation() {
        if (locationList.isLocationCreated(this.name)) {
            String outputResults = LINE;
            outputResults = offByApplianceLoop(outputResults);
            outputResults = outputResults.concat("All appliance in \"" + this.name + "\" are turned off ");
            return new CommandResult(outputResults);
        } else {
            return new CommandResult("No appliance in this location");
        }
    }

    private String offByApplianceLoop(String outputResults) {
        for (Appliance toOffAppliance : applianceList.getAllAppliance()) {
            if (toOffAppliance.getLocation().equals(this.name)) {
                outputResults = offAppliance(toOffAppliance, outputResults, true);
            }
        }
        return outputResults;
    }

    private String offAppliance(Appliance toOffAppliance, String outputResults, boolean isList) {
        boolean offResult = toOffAppliance.switchOff();
        assert toOffAppliance.getStatus().equals("OFF") : "Appliance should be already OFF";

        if (!isList) {
            if (offResult) {
                outputResults = LINE + "Switching: " + toOffAppliance + "......OFF";
            } else {
                outputResults = LINE + MESSAGE_APPLIANCE_PREVIOUSLY_OFF;
            }
        }
        return outputResults;
    }
}
