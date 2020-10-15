package seedu.smarthomebot.data;

import seedu.smarthomebot.data.framework.Appliance;
import seedu.smarthomebot.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.exceptions.InvalidAdditionOfAppliance;
import seedu.smarthomebot.ui.TextUi;

import java.util.ArrayList;

public class ApplianceList {

    private static ArrayList<Appliance> applianceList;
    private static TextUi ui = new TextUi();

    public ApplianceList() {
        applianceList = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) throws InvalidAdditionOfAppliance {
        if (!isApplianceExist(appliance.getName())) {
            applianceList.add(appliance);
        } else {
            throw new InvalidAdditionOfAppliance();
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
        return applianceList.get(index);
    }

    public ArrayList<Appliance> getAllAppliance() {
        return applianceList;
    }

    public void setAppliance(int index, Appliance appliance) {
        applianceList.set(index, appliance);
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
