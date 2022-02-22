import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


class PersonTest {

    @BeforeEach
    void setUp() {
        Person passenger = new Person();
    }

    @DisplayName("Test John Wick")
    @Test
    void getName() {
        Person passenger = new Person();
        String name = "John Wick";
        passenger.setName(name);

        assertEquals(passenger.getName(), name);
        assertNotEquals(passenger.getName(), "John Carter");
    }

    @Test
    void setName() {
        Person passenger = new Person();
        String name = "John Wick";
        passenger.setName(name);
        String name2 = "Batman";
        passenger.setName(name2);

        assertEquals(passenger.getName(), name2);
        assertNotEquals(passenger.getName(), name);

    }

    @Test
    void getEmail() {
        Person passenger = new Person();
        String email = "bill@yahoo.com";
        passenger.setEmail(email);

        assertEquals(passenger.getEmail(), email);
        assertNotEquals(passenger.getName(), "john@gmail.com");
    }

    @Test
    void setEmail() {
    }

    @Test
    void getPhoneNum() {
    }

    @Test
    void setPhoneNum() {
    }

    @Test
    void getGender() {
    }

    @Test
    void setGender() {
    }

    @Test
    void getAge() {
    }

    @Test
    void setAge() {
    }
}