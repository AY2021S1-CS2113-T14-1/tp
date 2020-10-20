package seedu.smarthomebot.data;

import seedu.smarthomebot.exceptions.DuplicateDataException;
import seedu.smarthomebot.exceptions.InvalidRemovalLocationException;

import java.util.ArrayList;

public class LocationList {

    private static ArrayList<String> locations;

    public LocationList() {
        locations = new ArrayList<>();
    }

    /**
     * Creating new location if is not existed.
     *
     * @param location used to be added into the location list
     */
    public void addLocation(String location) throws DuplicateDataException {
        // create location from Appliance
        if (!isLocationCreated(location)) {
            this.locations.add(location);
        } else {
            throw new DuplicateDataException();
        }
    }

    /**
     * Removing selected location with user input.
     *
     * @param location used to be removed from the location list
     */
    public void removeLocation(String location) throws InvalidRemovalLocationException {
        if (!(isLocationCreated(location))) {
            throw new InvalidRemovalLocationException();
        } else {
            int removeIndex = getRemoveLocationIndex(location);
            locations.remove(removeIndex);
        }
    }

    private int getRemoveLocationIndex(String location) {
        int removeIndex = -1;
        int locationIndex = 0;
        for (String l : locations) {
            if (l.equals(location)) {
                removeIndex = locationIndex;
                break;
            } else {
                locationIndex++;
            }
        }
        return removeIndex;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String l : locations) {
            sb.append(l);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns true if location string is not found.
     *
     * @param location used to identify the display index
     * @return isValid true if location is not found in list
     */
    public boolean isLocationCreated(String location) {
        boolean isValid = false;
        for (String l : locations) {
            if (l.equals(location)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

}
