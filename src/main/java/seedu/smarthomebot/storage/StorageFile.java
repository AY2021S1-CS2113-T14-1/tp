package seedu.smarthomebot.storage;

import seedu.smarthomebot.common.Messages;
import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.ApplianceList;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.Lights;
import seedu.smarthomebot.data.LocationList;
import seedu.smarthomebot.data.WaterHeater;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.FileCorrupted;
import seedu.smarthomebot.exceptions.InvalidAdditionOfAppliance;
import seedu.smarthomebot.exceptions.InvalidAddtionOfLocation;
import seedu.smarthomebot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;

public class StorageFile {

    private static final String filePath = "/data/SmartHomeBot.txt";
    private static ApplianceList applianceList;
    private static LocationList locationList;
    private final TextUi ui = new TextUi();

    public StorageFile(ApplianceList applianceList, LocationList locationList) {
        StorageFile.locationList = locationList;
        StorageFile.applianceList = applianceList;
    }

    public void writeToFile() {
        try {
            createFile();
            clearFile();
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(locationList.getLocations().toString() + "\n");
            for (int i = 0; i < applianceList.getAllAppliance().size(); i++) {
                myWriter.write(applianceList.getAppliance(i).getLocation()
                        + "|" + applianceList.getAppliance(i).getName()
                        + "|" + applianceList.getAppliance(i).getPower()
                        + "|" + applianceList.getAppliance(i).getType()
                        + "|" + applianceList.getAppliance(i).getStatus()
                        + "|" + applianceList.getAppliance(i).getPowerConsumption() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            ui.printToUser("An error occur");
        }
    }

    public void readFile() {
        try {
            int i = 0;
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            String locationList = myReader.nextLine();
            try {
                convertTextToLocationList(locationList);
                convertTextToApplianceList(i, myReader);
                ui.printToUser(Messages.MESSAGE_IMPORT);
            } catch (FileCorrupted e) {
                ui.printToUser(Messages.MESSAGE_FILE_CORRUPTED);
            }

            myReader.close();
        } catch (FileNotFoundException | EmptyParameterException | InvalidAdditionOfAppliance e) {
            ui.printToUser("Load File Does not Exist. No contents will be loaded.");
        }
    }

    private void convertTextToApplianceList(int i, Scanner myReader) throws FileCorrupted, InvalidAdditionOfAppliance {
        while (myReader.hasNextLine()) {
            try {
                String appliance = myReader.nextLine();
                String[] splitString = appliance.split("\\|", 7);
                if (splitString[1].isEmpty() || splitString[0].isEmpty()
                        || splitString[2].isEmpty() || splitString[3].isEmpty()) {
                    throw new FileCorrupted();
                }
                String name = splitString[1];
                String location = splitString[0];
                String power = splitString[2];
                String type = splitString[3];

                switch (type.toLowerCase()) {
                case Fan.TYPE_WORD:
                    Fan fan = new Fan(name, location, power);
                    applianceList.addAppliance(fan);
                    break;
                case AirConditioner.TYPE_WORD:
                    AirConditioner ac = new AirConditioner(name, location, power);
                    applianceList.addAppliance(ac);
                    break;
                case Lights.TYPE_WORD:
                    Lights light = new Lights(name, location, power);
                    applianceList.addAppliance(light);
                    break;
                case WaterHeater.TYPE_WORD:
                    WaterHeater waterheater = new WaterHeater(name, location, power);
                    applianceList.addAppliance(waterheater);
                    break;
                default:
                    ui.printToUser(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
                }

                applianceList.getAppliance(i).loadConsumptionFromFile(splitString[5]);
                if (splitString[4].toLowerCase().equals("on")) {
                    applianceList.getAppliance(i).switchOn();
                }
                i++;
            } catch (IndexOutOfBoundsException e) {
                throw new FileCorrupted();
            }

        }
    }

    private void convertTextToLocationList(String location) throws EmptyParameterException, FileCorrupted {
        try {
            int start = location.indexOf("[") + 1;
            int end = location.indexOf("]");
            String when = location.substring(start, end);
            String[] stringSplit = when.split(",");
            for (String locationName : stringSplit) {
                locationList.addLocation(locationName.trim());
            }
        } catch (IndexOutOfBoundsException | InvalidAddtionOfLocation e) {
            throw new FileCorrupted();
        }

    }

    public void createFile() {
        try {
            File myObj = new File(filePath);
            if (!myObj.getParentFile().exists()) {
                myObj.getParentFile().mkdirs();
            }
            if (myObj.exists()) {
                return;
            } else {
                myObj.createNewFile();
            }

        } catch (IOException e) {
            ui.printToUser("Unable to create file.");
        }
    }

    public void clearFile() {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            ui.printToUser("File is empty.");
        }
    }
}
