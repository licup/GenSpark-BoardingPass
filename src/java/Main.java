import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Person user = userInfo();
        Pass passPort = passInfo();
        System.out.println(user.toString());
        System.out.println(passPort.toString());
    }

    public static Person userInfo(){
        String name; String email; String phoneNum; Character gender; int age;
        //Input
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        name = input.nextLine();

        System.out.print("Enter your gender: ");
        gender = input.nextLine().charAt(0); // Make sure they follow the guidelines

        System.out.print("Enter your email: ");
        email = input.nextLine();

        System.out.print("Enter your phone number: ");
        phoneNum = input.nextLine();

        System.out.print("Enter your age: ");
        age = input.nextInt();

        return new Person(name,email,phoneNum,gender,age);
    }

    public static Pass passInfo(){
        String date; String origin; String destination; String departure;
        int passNum = (int)Math.floor((Math.random()*(1000 - 1)) + 1); // UNIQUE -> ?

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the date you want your flight to be: ");
        date = input.nextLine();

        System.out.print("Where will you be leaving from: ");
        origin = input.nextLine();

        System.out.print("Where will you want to fly to:");
        destination = input.nextLine();

        System.out.print("What time do you want to leave: ");
        departure = input.nextLine(); // Make sure that they follow a specific format HH:MM (Military time)

        //Creating ETA by using the Time class
        //Using a random range from 11:59PM (in milliseconds) to 12:01 AM (in milliseconds)
        long milli = (long)Math.floor((Math.random()*(86399999 - 59999))+ 59999);
        Time time = new Time(milli);
        String eta = time.toString().substring(0,5);

        return new Pass(passNum, date, origin, destination, eta, departure);
    }
}
