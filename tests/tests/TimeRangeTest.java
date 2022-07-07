package tests;

import main.model.Time;
import main.model.TimeRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeRangeTest {
    Time o1 = new Time(10, 00);
    Time o2 = new Time(12, 00);
    Time o3 = new Time(10, 30);

    @Test
    void tenToTenThirtyIsValidRange() {
        TimeRange i4 = new TimeRange(o1, o3);
        assertTrue(i4.isValidRange());
    }

    @Test
    void tenToMiddayIsValidRange(){
        TimeRange i1 = new TimeRange(o1, o2);
        assertTrue(i1.isValidRange());
    }

    @Test
    void middayToTenIsInvalidRange(){
        TimeRange i2 = new TimeRange(o2, o1);
        assertFalse(i2.isValidRange());
    }

    @Test
    void tenToTenIsValidRange(){
        TimeRange i3 = new TimeRange(o1, o1);
        assertTrue(i3.isValidRange());
    }


}