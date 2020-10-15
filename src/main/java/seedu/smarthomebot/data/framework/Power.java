package seedu.smarthomebot.data.framework;

import seedu.smarthomebot.ui.TextUi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static seedu.smarthomebot.common.Messages.LINE;
import static seedu.smarthomebot.common.Messages.MESSAGE_TIME_FORMAT_ERROR;

/**
 * Class representing the power consumption of appliances.
 */
public class Power {

    private static final String TIME_DATE_FORMAT = "dd/MM/yyyy | HH:mm:ss";
    private final int power;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_DATE_FORMAT);
    LocalDateTime currentTime;
    private String offTime;
    private String onTime;
    private double powerUsed;
    private double totalHours;
    private Boolean status;
    private double totalPowerConsumption;
    private final TextUi ui = new TextUi();

    public Power(String power) {
        this.power = Integer.parseInt(power);
        this.status = false;
        totalPowerConsumption = 0;
        totalHours = 0;
    }

    /**
     * Gets the status of the appliance.
     *
     * @return the status of the appliance in Boolean.
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Appliance only can be switched on if it was 'off' previously.
     *
     * @return true if appliance turn on successfully
     */
    public boolean onAppliance() {
        if (!status) {
            this.status = true;
            onTime = getCurrentTime();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Appliance only can be switched off if it was 'on' previously.
     * Compute the total power consumption once appliance is off.
     *
     * @return true if appliance turn off successfully
     */
    public boolean offAppliance() {
        if (status) {
            this.status = false;
            offTime = getCurrentTime();
            try {
                calculatePowerConsumed();
            } catch (ParseException e) {
                ui.printToUser(LINE + MESSAGE_TIME_FORMAT_ERROR);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the power consumption of appliance.
     *
     * @return the power consumption in double.
     */
    public double getPower() {
        try {
            calculatePowerConsumed();
        } catch (ParseException e) {
            ui.printToUser(LINE + MESSAGE_TIME_FORMAT_ERROR);
        }
        return this.totalPowerConsumption;
    }

    /**
     * Computes power consumption from loaded file.
     */
    public void loadConsumptionFromFile(double powerConsumption) {
        powerUsed = powerConsumption;
        totalPowerConsumption += powerUsed;
    }

    /**
     * Gets the power consumption in String.
     *
     * @return the power consumption in String.
     */
    public String toString() {
        return String.valueOf(this.totalPowerConsumption);
    }

    private String getCurrentTime() {
        currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    private void calculateTimeUsed() throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_DATE_FORMAT);
        Date onTimeValue;
        Date offTimeValue;
        double timeUsed;

        if (onTime != null) {
            onTimeValue = timeFormat.parse(onTime);
            if (!this.status) {
                offTimeValue = timeFormat.parse(offTime);
                timeUsed = offTimeValue.getTime() - onTimeValue.getTime();
                onTime = offTime;
            } else {
                Date currentUsedTime = timeFormat.parse(getCurrentTime());
                timeUsed = currentUsedTime.getTime() - onTimeValue.getTime();
                onTime = getCurrentTime();
            }
        } else {
            timeUsed = 0;
        }

        // For simulation purpose, 1 second in System equals to 1 hour in SmartHomeBot
        // Convert back to hours timeDifference/(1000 * 60 * 60)
        totalHours = timeUsed / (1000);
    }

    private void calculatePowerConsumed() throws ParseException {
        calculateTimeUsed();
        // Convert power unit to kWh
        powerUsed = totalHours * power / 1000.00;
        totalPowerConsumption += powerUsed;
    }

}
