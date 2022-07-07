package tests;

import main.model.NativeField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NativeFieldTest {

    NativeField cn = new NativeField(true, NativeField.Tipo.STRING);

    @Test
    void isObbligatorio() {
        assertTrue(cn.isObbligatorio());
    }

    @Test
    void getType(){
        assertEquals(NativeField.Tipo.STRING, cn.getType());
    }
}