package seedu.smarthomebot.commands;

import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.Lights;
import seedu.smarthomebot.data.WaterHeater;
import seedu.smarthomebot.exceptions.InvalidAdditionOfAppliance;
import seedu.smarthomebot.exceptions.LocationNotFoundException;

import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;
import static seedu.smarthomebot.common.Messages.MESSAGE_LOCATION_NOT_EXIST;


public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new appliance to the particular location to the SmartHomeBot. \n"
            + "Parameters: add NAME l/LOCATION w/WATTS t/TYPE_OF_APPLIANCE \n" + "Example: "
            + COMMAND_WORD + " Fan1 l/Bedroom 1 w/50 t/Fan";

    private final String name;
    private final String location;
    private final String power;
    private final String type;

    public AddCommand(String name, String location, String power, String type) {
        this.name = name;
        this.location = location;
        this.power = power;
        this.type = type;
    }


    @Override
    public CommandResult execute() {
        try {
            if (!locationList.isLocationCreated(this.location)) {
                throw new LocationNotFoundException();
            }
            switch (type.toLowerCase()) {
            case Fan.TYPE_WORD:
                Fan fan = new Fan(name, location, power);
                applianceList.addAppliance(fan);
                return new CommandResult("ADDING " + fan.toString() + "......ADDED");
            case AirConditioner.TYPE_WORD:
                AirConditioner ac = new AirConditioner(name, location, power);
                applianceList.addAppliance(ac);
                return new CommandResult("ADDING " + ac.toString() + "......ADDED");
            case Lights.TYPE_WORD:
                Lights light = new Lights(name, location, power);
                applianceList.addAppliance(light);
                return new CommandResult("ADDING " + light.toString() + "......ADDED");
            case WaterHeater.TYPE_WORD:
                WaterHeater waterheater = new WaterHeater(name, location, power);
                applianceList.addAppliance(waterheater);
                return new CommandResult("ADDING " + waterheater.toString() + "......ADDED");
            default:
                return new CommandResult(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
            }
        } catch (InvalidAdditionOfAppliance e) {
            return new CommandResult(MESSAGE_APPLIANCE_EXIST);

        } catch (LocationNotFoundException e) {
            return new CommandResult(MESSAGE_LOCATION_NOT_EXIST);
        }
    }
}
