package seedu.smarthomebot.data.framework.type;

import seedu.smarthomebot.data.framework.Appliance;

import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_TEMPERATURE_AC;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_TEMPERATURE_WH;

public class WaterHeater extends Appliance {

    public static final String TYPE_WORD = "waterheater";
    private static final int LOWER_BOUND = 20;
    private static final int UPPER_BOUND = 50;
    private String temperature;

    public WaterHeater(String name, String location, String wattage) {
        super(name, location, wattage);
        this.temperature = "40";
    }

    public String setTemperature(String temperature) {
        String toPrint;
        if (temperature.isEmpty()) {
            // default temperature will be set.
            toPrint = toString();
        } else if (isParameterValid(temperature, LOWER_BOUND, UPPER_BOUND)) {
            // user input speed will be set.
            this.temperature = temperature;
            toPrint = toString();
        } else {
            // user inputs an invalid temperature.
            toPrint = "\n" + MESSAGE_INVALID_TEMPERATURE_WH;
        }
        return toPrint;
    }

    public String getType() {
        return TYPE_WORD;
    }

    @Override
    public String getParameter(boolean isList) {
        if (isList) {
            return " set at: " + getParameter(false) + " degrees";
        } else {
            return this.temperature;
        }
    }

    public void getTemperatureFromLoadFile(String loadedTemperature) {
        temperature = loadedTemperature;
    }


    public String toString() {
        String temperatureStatement;
        if (appliancePower.getStatus()) {
            temperatureStatement = getParameter(true);
        } else {
            temperatureStatement = "";
        }

        return super.toString() + temperatureStatement;
    }


    @Override
    public String writeFileFormat() {
        return super.writeFileFormat();
    }

}