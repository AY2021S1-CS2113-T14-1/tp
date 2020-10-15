package seedu.smarthomebot.common;

public class Messages {

    public static final String LINE = "-".repeat(Math.max(0, 54)) + "\n";
    public static final String DIVIDER = "=".repeat(Math.max(0, 50));
    public static final String MESSAGE_WELCOME = "Welcome to your SmartHomeBot V1.0!";
    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_IMPORT = "Importing data........Completed!";
    public static final String MESSAGE_EXPORT = "Exporting data........Completed!";
    public static final String MESSAGE_APPLIANCE_TYPE_NOT_EXIST = "Type Entered does not exist.";
    public static final String MESSAGE_APPLIANCE_EXIST = "Appliance name already exists.";
    public static final String MESSAGE_LOCATION_NOT_EXIST = "Location does not exist.";
    public static final String MESSAGE_DISPLAY_LOCATION = " | Location: ";
    public static final String MESSAGE_DISPLAY_STATUS = " | Status: ";
    public static final String MESSAGE_DISPLAY_USAGE = " | Usage: ";
    public static final String MESSAGE_DISPLAY_WATT = " | Watt: ";
    public static final String MESSAGE_DISPLAY_TYPE = " | Type: ";
    public static final String MESSAGE_LIST_LOCATIONS = "Here are the location you have entered.";
    public static final String MESSAGE_LIST_APPLIANCES = "Here are the appliances in your list.";
    public static final String MESSAGE_POWER_USAGE = "Here are the power usage consumption.";
    public static final String MESSAGE_LIST_NO_LOCATIONS = "There is currently no locations in the list.";
    public static final String MESSAGE_LIST_NO_APPLIANCES = "There is currently no appliances in the list.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =  "Invalid Command Format";
    public static final String MESSAGE_INVALID_ADD_COMMAND =  "Please follow order of AddCommand";
    public static final String MESSAGE_INVALID_LIST_COMMAND =
            "Please enter either 'list appliance' or 'list location'";
    public static final String MESSAGE_POWER_NOT_NUMBER =  "Please enter a valid numerical value for power.";
    public static final String MESSAGE_POWER_EXCEEDED =
            "Appliance wattage is uncommon. Please enter between 0 to 9999.";
    public static final String MESSAGE_TIME_FORMAT_ERROR = "Time format is wrong.";
    public static final String MESSAGE_APPLIANCE_PREVIOUSLY_ON = "The appliance is already ON previously.";
    public static final String MESSAGE_APPLIANCE_PREVIOUSLY_OFF = "The appliance is already OFF previously.";
    public static final String MESSAGE_FILE_CORRUPTED = "Data file is corrupted, some data will not be entered";

    public static final String MESSAGE_APPLIANCE_NOT_EXIST = "Appliance does not exist in the list.";
}
