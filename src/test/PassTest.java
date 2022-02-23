import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class PassTest {

    @BeforeEach
    void setUp() {
        Pass ticket = new Pass();
    }

    @DisplayName("Test PassNumber: 5478")
    @Test
    void getPassNum() {
        Pass ticket = new Pass();
        int passNumber = 5478;
        ticket.setPassNum(passNumber);

        assertEquals(5478, ticket.getPassNum());
        assertNotEquals(9987, ticket.getPassNum());
    }

    @DisplayName("Test PassNumber: 1111")
    @Test
    void setPassNum() {
        Pass ticket = new Pass();
        int passNumber = 8669;
        ticket.setPassNum(passNumber);
        int newPassNumber = 1111;
        ticket.setPassNum(newPassNumber);

        Pass ticket2 = null;
        assertNull(ticket2);

        assertEquals(1111, ticket.getPassNum());
        assertTrue(ticket.getPassNum() <= 9999);
        assertNotEquals(8669, ticket.getPassNum());
    }

    @DisplayName("Test Purchase_date: 08/16/2022")
    @Test
    void getDate() {
        Pass ticket = new Pass();
        String date = "08/16/2022";
        ticket.setDate(date);

        assertEquals("08/16/2022", ticket.getDate());
        assertTrue(ticket.getDate().length() == 10 || ticket.getDate().length() == 9);
        assertNotEquals("10/1/2021", ticket.getDate());
    }

    @DisplayName("Test Purchase_date: 11/5/2023")
    @Test
    void setDate() {
        Pass ticket = new Pass();
        String date = "08/16/2022";
        ticket.setDate(date);
        String newDate = "11/5/2023";
        ticket.setDate(newDate);

        assertEquals("11/5/2023", ticket.getDate());
        assertNotEquals("08/16/2022", ticket.getDate());
    }

    @DisplayName("Test Origin: New York, NY")
    @Test
    void getOrigin() {
        Pass ticket = new Pass();
        String origin = "New York, NY";
        ticket.setOrigin(origin);

        assertEquals("New York, NY", ticket.getOrigin());
        assertNotEquals(null, ticket.getOrigin());
    }

    @DisplayName("Test Origin: Denver, CO")
    @Test
    void setOrigin() {
        Pass ticket = new Pass();
        String origin = "Charlotte, NC";
        ticket.setOrigin(origin);
        String newOrigin = "Denver, CO";
        ticket.setOrigin(newOrigin);

        assertEquals("Denver, CO", ticket.getOrigin());
        assertNotEquals("Charlotte, NC", ticket.getOrigin());
    }
    @DisplayName("Test Destination: New York, NY")
    @Test
    void getDestination() {
        Pass ticket = new Pass();
        String destination = "San Diego, CA";
        ticket.setDestination(destination);

        assertEquals("San Diego, CA", ticket.getDestination());
        assertNotEquals(null, ticket.getDestination());
    }

    @DisplayName("Test Destination: Beijing, China")
    @Test
    void setDestination() {
        Pass ticket = new Pass();
        String destination = "Bing Bong, NJ";
        ticket.setDestination(destination);
        String newDestination = "Beijing, China";
        ticket.setDestination(newDestination);

        assertEquals("Beijing, China", ticket.getDestination());
        assertNotEquals("Bejing, China", ticket.getDestination());
    }

    @DisplayName("Test ETA: 14:21")
    @Test
    void getEta() {
        Pass ticket = new Pass();
        String eta = "14:21";
        ticket.setEta(eta);

        assertEquals(eta, ticket.getEta());
        assertNotEquals("20:33", ticket.getEta());
    }

    @DisplayName("Test ETA: 11:52")
    @Test
    void setEta() {
        Pass ticket = new Pass();
        String eta = "10:44";
        ticket.setEta(eta);
        String delayed = "11:52";
        ticket.setEta(delayed);

        assertEquals(delayed, ticket.getEta());
        assertNotEquals(eta, ticket.getEta());
    }

    @DisplayName("Test Departure: 15:00")
    @Test
    void getDeparture() {
        Pass ticket = new Pass();
        String departure = "15:00";
        ticket.setDeparture(departure);

        assertEquals(departure, ticket.getDeparture());
        assertNotEquals("20:33", ticket.getDeparture());
    }

    @DisplayName("Test Departure: 17:45")
    @Test
    void setDeparture() {
        Pass ticket = new Pass();
        String departure = "17:00";
        ticket.setDeparture(departure);
        String delay = "17:45";
        ticket.setDeparture(delay);

        assertEquals(delay, ticket.getDeparture());
        assertNotEquals(departure, ticket.getDeparture());
    }
}