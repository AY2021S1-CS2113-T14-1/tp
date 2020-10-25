package seedu.smarthomebot.data.appliance;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;

import java.util.ArrayList;

public class ApplianceList {

    private static ArrayList<Appliance> applianceList;

    public ApplianceList() {
        applianceList = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) throws DuplicateDataException {
        if (!isApplianceExist(appliance.getName())) {
            applianceList.add(appliance);
        } else {
            throw new DuplicateDataException();
        }
    }

    public Appliance removeAppliance(String userEnteredName) throws ApplianceNotFoundException {
        for (Appliance appliance : this.getAllAppliance()) {
            if (appliance.getName().equals(userEnteredName)) {
                applianceList.remove(appliance);
                return appliance;
            }
        }
        throw new ApplianceNotFoundException();
    }

    public Appliance getAppliance(int index) {
        assert index < 0 : "Index should be positive.";
        return applianceList.get(index);
    }

    public ArrayList<Appliance> getAllAppliance() {
        return applianceList;
    }

    public Boolean isApplianceExist(String toAddApplianceName) {
        boolean isExist = false;
        for (Appliance a : applianceList) {
            if (a.getName().equals(toAddApplianceName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public void deleteByLocation(String usersEnteredLocation) throws ApplianceNotFoundException {
        for (int x = this.getAllAppliance().size() - 1; x >= 0; x--) {
            if (this.getAppliance(x).getLocation().equals(usersEnteredLocation)) {
                this.removeAppliance((this.getAppliance(x).getName()));
            }
        }
    }
    
}
