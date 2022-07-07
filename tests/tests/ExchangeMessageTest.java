package tests;

import main.model.Customer;
import main.model.ExchangeMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ExchangeMessageTest {
    ExchangeMessage em = new ExchangeMessage("Messaggio", new Customer("User", "userpw"));

    @Test
    void canReadMessageField(){
        assertEquals("Messaggio", em.getMessage());
    }

    @Test
    void canReadNotNullAuthor(){
        assertNotNull(em.getAuthor());
    }
}
