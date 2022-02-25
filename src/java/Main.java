import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.sql.*;

public class Main {

    // ************************* MAIN ************************* //
    public static void main(String[] args) {
        System.out.println("\n" + "Welcome to Boarding Pass Signup Portal." + "\n" + "\n"
                            + "Please provide us with your information." + "\n"
                            + "Lastly we will show you the result that you looking for." + "\n"
        );
        Person user = userInfo();
        Pass passPort = passInfo(user);
        writeIntoFile(user, passPort);
        System.out.println(output(passPort.getPassNum()));
    }


    // ************************* USER INFO SECTION ************************* //
    public static Person userInfo() {
        // User info Init.
        String name; String email; String phoneNum; Character gender; int age;

        // Scanner Class to catch input from user.
        Scanner input = new Scanner(System.in);

        // Take in user's full name.
        String nameFormat = "^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$";
        System.out.print("Enter your first and last name: ");
        name = input.nextLine();
        if(!name.matches(nameFormat)) {
            while (!name.matches(nameFormat)) {
                System.out.println("You entered the wrong format!\n" +
                        "Example: Leo Leo");
                System.out.print("Enter your first and last name: ");
                name = input.nextLine();
            }
        }

        // Take in user's gender.
        System.out.print("Enter your gender (M(Male), F(Female), O(Prefer Not to Say)): ");
        gender = input.nextLine().charAt(0);
        if(!gender.toString().equalsIgnoreCase("F") && !gender.toString().equalsIgnoreCase("M") && !gender.toString().equalsIgnoreCase("O")){
            while(!gender.toString().equalsIgnoreCase("F") && !gender.toString().equalsIgnoreCase("M") && !gender.toString().equalsIgnoreCase("O")){
                System.out.println("Please enter either M, F, or O!");
                System.out.print("Enter your gender (M(Male), F(Female), O(Prefer Not to Say)): ");
                gender = input.nextLine().charAt(0);
            }
        }

        // Take in user's email.
        String emailFormat = "^(.+)@(.+)$";
        System.out.print("Enter your email: ");
        email = input.nextLine();
        if(!email.matches(emailFormat)) {
            while (!email.matches(emailFormat)) {
                System.out.println("You entered the wrong format!\n" +
                        "Example: abc@abc.abc");
                System.out.print("Enter your email: ");
                email = input.nextLine();
            }
        }
      
        // Take in user's phone number.
        String phoneNumFormat = "[0-9]{10}";
        System.out.print("Enter your phone number: ");
        phoneNum = input.nextLine();
        if(!phoneNum.matches(phoneNumFormat)) {
            while (!phoneNum.matches(phoneNumFormat)) {
                System.out.println("You entered the wrong format!\n" +
                        "Example: 1234567890");
                System.out.print("Enter your phone number: ");
                phoneNum = input.nextLine();
            }
        }

        // Take in user's age.
        System.out.print("Enter your age: ");
        age = input.nextInt();

        // Last, generate a person details with user's inputs from above.
        return new Person(name, email, phoneNum, gender, age);
    }


    // ************************* USER INFO SECTION ************************* //
    public static Pass passInfo(Person person) {
        // User pass init.
        String date; String origin; String destination; String departure;

        // User pass generator.
        int passNum = (int)Math.floor((Math.random()*(999999999 - 100000000)) + 1);
        //If the pass number already exists in the passenger_log.txt, generates a new number
        if(!isUniqueNum(passNum)){
            passNum = (int)Math.floor((Math.random()*(999999999 - 100000000)) + 1);
        }

        // Scanner class to catch user input.
        Scanner input = new Scanner(System.in);

        // Take in user's input for date.
        String dateFormat = "[0-9]{2}+/+[0-9]{2}+/+[0-9]{4}";
        System.out.print("Enter the date you want your flight to be (MM/DD/YYYY): ");
        date = input.nextLine();
        if(!date.matches(dateFormat) || !dateCheck(date)) {
            while (!date.matches(dateFormat) || !dateCheck(date)) {
                System.out.println("You entered an Invalid Date!\n" +
                        "Example: 01/01/2022");
                System.out.print("Enter the date you want your flight to be (MM/DD/YYYY): ");
                date = input.nextLine();
            }
        }


        // Take in user's input for where user will be leaving from.
        String areaFormat = "^[a-zA-Z]*$";
        System.out.print("Where will you be leaving from: ");
        origin = input.nextLine();
        if(!origin.matches(areaFormat)){
            while(!origin.matches(areaFormat)){
                System.out.println("Please enter a valid destination!\nExample: Chicago");
                System.out.print("Where will you be leaving from: ");
                origin = input.nextLine();
            }
        }
        
        // Take in user's input for where user want to fly to.
        System.out.print("Where will you want to fly to: ");
        destination = input.nextLine();
        if(!destination.matches(areaFormat)){
            while(!destination.matches(areaFormat)){
                System.out.println("Please enter a valid destination!\nExample: Chicago");
                System.out.print("Where will you be leaving from: ");
                destination = input.nextLine();
            }
        }

        // Take in user's input for time.
        String timeFormat = "[0-9]{2}+:+[0-9]{2}";
        System.out.print("What time do you want to leave (Military Time): ");
        departure = input.nextLine(); // Make sure that they follow a specific format HH:MM (Military time)
        if(!departure.matches(timeFormat)) {
            while (!departure.matches(timeFormat)) {
                System.out.println("You entered the wrong format!\n" +
                        "Example: 03:30");
                System.out.print("What time do you want to leave (Military Time): ");
                departure = input.nextLine();
            }
        }

        //Creating ETA by using the Time class
        //Using a random range from 11:59PM (in milliseconds) to 12:01 AM (in milliseconds)
        long milli = (long)Math.floor((Math.random()*(86399999 - 59999))+ 59999);
        Time time = new Time(milli);
        String eta = time.toString().substring(0,5);

        // Generate random price within 100 to 1000 for the user.
        double randomPrice = Math.floor(((100.0 + Math.random( ) * 1000.0) * 10)/10);
        System.out.println(randomPrice);
        //Reduces price based on if the person:
        //Age >= 60
        //Age <= 12
        //Gender = Female
        if(person.isOld()) {
            randomPrice -= randomPrice*.6;
        } else if(person.isGirl()) {
            randomPrice -= randomPrice*.25;
        } else if(person.isYoung()) {
            randomPrice -= randomPrice*.5;
        }
        System.out.println("This is the price of the trip: "+ randomPrice);
        String finalPrice = String.format("%.2f", randomPrice);

        // Last, generate pass info from the user's input from above.
        return new Pass(passNum, date, origin, destination, eta, departure, Double.parseDouble(finalPrice));
    }


    // ************************* WRITE TO FILE SECTION ************************* //
    public static void writeIntoFile(Person user, Pass pass) {
        try {
            File file = new File("passenger_log.txt");
            //If the file doesn't exist then it'll create a passenger_log.txt in the project
            //If the file already exists, then it will append the info given by the user into the txt file
            if (file.createNewFile()) {
                String dataLine = pass.getPassNum() + ", " + user.getName() + ", " + user.getAge() + ", " + user.getGender() + ", " + user.getEmail() + ", " + user.getPhoneNum()
                        + ", " + pass.getDate() + ", " + pass.getOrigin() + ", " + pass.getDestination() + ", " + pass.getDeparture() + ", " + pass.getEta() + ", " + pass.getPrice() + "\n";
                Files.write(Paths.get("passenger_log.txt"), dataLine.getBytes(), StandardOpenOption.APPEND);
            }else{
                String dataLine = pass.getPassNum() + ", " + user.getName() + ", " + user.getAge() + ", " + user.getGender() + ", " + user.getEmail() + ", " + user.getPhoneNum()
                        + ", " + pass.getDate() + ", " + pass.getOrigin() + ", " + pass.getDestination() + ", " + pass.getDeparture() + ", " + pass.getEta() + ", " + pass.getPrice() + "\n";
                Files.write(Paths.get("passenger_log.txt"), dataLine.getBytes(), StandardOpenOption.APPEND);
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    // ************************* CHECK IF UNIQUE PASS METHOD ************************* //
    public static Boolean isUniqueNum(int num){
        try {
            File file = new File("passenger_log.txt");
            Scanner scan = new Scanner(file);

            while(scan.hasNextLine()){
                List<String> line = Arrays.asList(scan.nextLine().split(","));
                Integer number = Integer.parseInt(line.get(0));

                if(number.equals(num)){
                    return false;
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }

        return true;
    }

    // ************************* WRITE TO FILE METHOD ************************* //
    public static String output(int passNum) {
        try {
            File file = new File("passenger_log.txt");
            Scanner scan = new Scanner(file);

            while(scan.hasNextLine()) {
                List<String> line = Arrays.asList(scan.nextLine().split(","));
                Integer numCheck = Integer.parseInt(line.get(0));

                if(numCheck.equals(passNum)) {
                    return "BoardingPass Number:" + line.get(0) + "\n" +
                            "Name:" + line.get(1) + " Age:" + line.get(2) + " Gender:" +
                            line.get(3) + " Email:" + line.get(4) + " Phone Number:" + line.get(5) +
                            "\nDate:" + line.get(6) + " Origin:" + line.get(7) + " Destination:" + line.get(8)
                            + " Estimated Time of Arrival(ETA):" + line.get(9) + "\nTime of Departure:" + line.get(10)
                            + "\nTotal Price: $" + String.format("%.2f", Double.parseDouble(line.get(11)));
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }

        return null;
    }

    // ************************* DATE CHECK METHOD ************************* //
    public static Boolean dateCheck(String date) {
        List<String> dateSplit = Arrays.asList(date.split("/"));

        // 0 specify the months, 1 specify the days, 2 specify the year.
        if(
            (Integer.parseInt(dateSplit.get(0)) < 1 || Integer.parseInt(dateSplit.get(0)) > 12)
            || (Integer.parseInt(dateSplit.get(1)) < 1 || Integer.parseInt(dateSplit.get(1)) > 31)
            || (Integer.parseInt(dateSplit.get(0)) == 2 && Integer.parseInt(dateSplit.get(1)) > 29)
        ) {
            return false;
        }

        return true;
    }
}
