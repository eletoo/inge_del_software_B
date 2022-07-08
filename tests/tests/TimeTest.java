package tests;

import main.model.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    //ora valida, test sui minuti
    Time o1 = new Time(10, 00);
    Time o2 = new Time(10, 01);
    Time o3 = new Time(10, 59);
    Time o4 = new Time(24, 00);
    Time o5 = new Time(00, 00);
    Time o13 = new Time(10, -1);
    Time o14 = new Time(10, 60);
    Time o6 = new Time(10, 30);
    Time o7 = new Time(10, 29);
    Time o8 = new Time(10, 31);


    @Test
    void tenIsvalidTime() {
        assertTrue(o1.isValid());
    }

    @Test
    void onePastTenIsInvalidTime() {
        assertFalse(o2.isValid());
    }

    @Test
    void tenFiftynineIsInvalidTime() {
        assertFalse(o3.isValid());
    }

    @Test
    void midnightAtTwentyfourIsValidTime() {
        assertTrue(o4.isValid());
    }

    @Test
    void midnightIsValidTime() {
        assertTrue(o5.isValid());
    }

    @Test
    void tenAndMinusOneIsInvalid() {
        assertFalse(o13.isValid());
    }

    @Test
    void tenSixtyIsInvalidTime() {
        assertFalse(o14.isValid());
    }

    @Test
    void tenThirtyIsValidTime() {
        assertTrue(o6.isValid());
    }

    @Test
    void tenTwentyNineIsInvalidTime() {
        assertFalse(o7.isValid());
    }

    @Test
    void tenThirtyOneIsInvalidTime() {
        assertFalse(o8.isValid());
    }

    //minuti validi, test sulle ore
    Time o9 = new Time(01, 00);
    Time o10 = new Time(23, 00);
    Time o11 = new Time(25, 00);
    Time o12 = new Time(-1, 00);

    @Test
    void oneOClockIsValidTime() {
        assertTrue(o9.isValid());
    }

    @Test
    void twentyThreeIsValidTime() {
        assertTrue(o10.isValid());
    }

    @Test
    void twentyFiveIsInvalidTime() {
        assertFalse(o11.isValid());
    }

    @Test
    void minusOneIsInvalidTime() {
        assertFalse(o12.isValid());
    }
}