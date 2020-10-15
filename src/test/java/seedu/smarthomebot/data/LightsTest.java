package seedu.smarthomebot.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LightsTest {

    @Test
    void getType_nullInput_returnsLight() {
        assertEquals("Light", new Lights("light 1", "bedroom 1", "200").getType());
    }

    @Test
    void toString_nullInput_returnsName_and_status() {
        assertEquals("light 1(200W) in bedroom 1", new Lights("light 1", "bedroom 1", "200").toString());
    }
}