package seedu.smarthomebot.model.type;

import org.junit.jupiter.api.Test;
import seedu.smarthomebot.model.framework.type.Fan;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FanTest {

    @Test
    void getType_nullInput_returnsFan() {
        assertEquals("fan", new Fan("Fan 1", "bedroom 1", "200").getType());
    }

}