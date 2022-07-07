package tests;

import main.model.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    Time o1 = new Time(10, 00);
    Time o2 = new Time(10, 10);
    Time o3 = new Time(10, 30);
    Time o4 = new Time(24, 00);
    Time o5 = new Time(00, 00);

    @Test
    void tenIsvalidTime() {
        assertTrue(o1.isValid(o1.getHour(), o1.getMinutes()));
    }

    @Test
    void tenPastTenIsInvalidTime(){
        assertFalse(o2.isValid(o2.getHour(), o2.getMinutes()));
    }

    @Test
    void tenThirtyIsValidTime(){
        assertTrue(o3.isValid(o3.getHour(), o3.getMinutes()));
    }

    @Test
    void midnightAtTwentyfourIsValidTime(){
        assertTrue(o4.isValid(o4.getHour(), o4.getMinutes()));
    }

    @Test
    void midnightIsValidTime(){
        assertTrue(o5.isValid(o5.getHour(), o5.getMinutes()));
    }
}