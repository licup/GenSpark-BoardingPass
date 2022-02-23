import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


class PersonTest {

    @BeforeEach
    void setUp() {
        Person passenger = new Person();
    }

    @DisplayName("Test Name: John Wick")
    @Test
    void getName() {
        Person passenger = new Person();
        String name = "John Wick";
        passenger.setName(name);

        assertEquals(passenger.getName(), name);
        assertNotEquals(passenger.getName(), "John Carter");
    }

    @DisplayName("Test Name: Batman")
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

    @DisplayName("Test Email: billy@yahoo.com")
    @Test
    void getEmail() {
        Person passenger = new Person();
        String email = "bill@yahoo.com";
        passenger.setEmail(email);

        assertEquals(passenger.getEmail(), email);
        assertNotEquals(passenger.getName(), "john@gmail.com");
    }

    @DisplayName("Test Email: lucky@yahoo.com")
    @Test
    void setEmail() {
        Person passenger = new Person();
        String email = "kelly@gmail.com";
        passenger.setEmail(email);
        String newEmail = "lucky@yahoo.com";
        passenger.setEmail(newEmail);

        assertEquals(newEmail, passenger.getEmail());
        assertNotEquals(email, passenger.getEmail());
    }

    @DisplayName("Test Number: 9806495547")
    @Test
    void getPhoneNum() {
        Person passenger = new Person();
        String phoneNum = "9806495547";
        passenger.setPhoneNum(phoneNum);

        assertEquals(10, phoneNum.length());
        assertEquals(phoneNum, passenger.getPhoneNum());
        assertNotEquals("1723365472", passenger.getName());
    }

    @DisplayName("Test Number: 8087459962")
    @Test
    void setPhoneNum() {
        Person passenger = new Person();
        String phoneNum = "9801452315";
        passenger.setPhoneNum(phoneNum);
        String phoneNum2 = "8087459962";
        passenger.setPhoneNum(phoneNum2);

        assertEquals(phoneNum2, passenger.getPhoneNum());
        assertNotEquals(phoneNum, passenger.getPhoneNum());
    }

    @DisplayName("Test Gender: F")
    @Test
    void getGender() {
        Person passenger = new Person();
        Character gender = 'F';
        passenger.setGender(gender);

        assertEquals('F', passenger.getGender());
        assertNotEquals("M", passenger.getGender());
    }

    @DisplayName("Test Gender: M")
    @Test
    void setGender() {
        Person passenger = new Person();
        Character gender = 'F';
        passenger.setGender(gender);
        Character newGender = 'M';
        passenger.setGender(newGender);

        assertEquals('M', passenger.getGender());
        assertNotEquals('F', passenger.getGender());
    }

    @DisplayName("Test Age: 29")
    @Test
    void getAge() {
        Person passenger = new Person();
        int age = 29;
        passenger.setAge(age);

        assertEquals(29, passenger.getAge());
        assertNotEquals(0, passenger.getAge());
    }

    @DisplayName("Test Age: 37")
    @Test
    void setAge() {
        Person passenger = new Person();
        int age = 36;
        passenger.setAge(age);
        int newAge = 37;
        passenger.setAge(newAge);

        assertEquals(37, passenger.getAge());
        assertNotEquals(36, passenger.getAge());
    }
}