import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Person user = userInfo();
        Pass passPort = passInfo(user);
        writeIntoFile(user, passPort);
        System.out.println(output(passPort.getPassNum()));
    }

    public static Person userInfo(){
        String name; String email; String phoneNum; Character gender; int age;
        //Input
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        name = input.nextLine();

        System.out.print("Enter your gender: M(Male), F(Female), O(Prefer Not to Say) ");
        gender = input.nextLine().charAt(0);
        // Make sure they follow the guidelines
        if(!gender.toString().toUpperCase().equals("F") && !gender.toString().toUpperCase().equals("M") && !gender.toString().toUpperCase().equals("O")){
            while(!gender.toString().toUpperCase().equals("F") && !gender.toString().toUpperCase().equals("M") && !gender.toString().toUpperCase().equals("O")){
                System.out.println("Please enter either M, F, or O!");
                System.out.print("Enter your gender: M(Male), F(Female), O(Prefer Not to Say) ");
                gender = input.nextLine().charAt(0);
            }
        }
        //grabbed email regex from https://blog.lkatney.com/2015/02/28/regex-to-check-email-address-username-format-apex/
        String emailFormat = "([a-zA-Z0-9_\\-\\\\.]+)@((\\[a-z]{1,3}\\.[a-z]{1,3}\\.[a-z]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})";
        System.out.print("Enter your email: ");
        email = input.nextLine();
        if(!email.matches(emailFormat)){
            while(!email.matches(emailFormat)){
                System.out.println("Please Enter a valid email!\nExample: user1@example.com");
                System.out.print("Enter your email: ");
                email = input.nextLine();
            }
        }
        String phoneNumFormat = "[0-9]{10}";
        System.out.print("Enter your phone number: ");
        phoneNum = input.nextLine();
        if(!phoneNum.matches(phoneNumFormat)) {
            while (!phoneNum.matches(phoneNumFormat)) {
                System.out.println("You entered the wrong format!\n" +
                        "Example: 1234567890");
                System.out.print("Enter your phone number:  ");
                phoneNum = input.nextLine();
            }
        }
        System.out.print("Enter your age: ");
        age = input.nextInt();

        return new Person(name,email,phoneNum,gender,age);
    }

    public static Pass passInfo(Person person){
        String date; String origin; String destination; String departure;
        int passNum = (int)Math.floor((Math.random()*(1000 - 1)) + 1);
        //If the pass number already exists in the passenger_log.txt, generates a new number
        if(!isUniqueNum(passNum)){
            passNum = (int)Math.floor((Math.random()*(1000 - 1)) + 1);
        }

        Scanner input = new Scanner(System.in);

        String dateFormat = "[0-9]{2}+/+[0-9]{2}+/+[0-9]{4}";
        System.out.print("Enter the date you want your flight to be (MM/DD/YYYY) : ");
        date = input.nextLine();
        if(!date.matches(dateFormat) || !dateCheck(date)) {
            while (!date.matches(dateFormat) || !dateCheck(date)) {
                System.out.println("You entered an Invalid Date!\n" +
                        "Example: 01/01/2022");
                System.out.print("Enter the date you want your flight to be (MM/DD/YYYY) : ");
                date = input.nextLine();
            }
        }

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

        System.out.print("Where will you want to fly to: ");
        destination = input.nextLine();
        if(!destination.matches(areaFormat)){
            while(!destination.matches(areaFormat)){
                System.out.println("Please enter a valid destination!\nExample: Chicago");
                System.out.print("Where will you be leaving from: ");
                destination = input.nextLine();
            }
        }
        String timeFormat = "[0-9]{2}+:+[0-9]{2}";
        System.out.print("What time do you want to leave (Military Time): ");
        departure = input.nextLine(); // Make sure that they follow a specific format HH:MM (Military time)
        if(!departure.matches(timeFormat)) {
            while (!departure.matches(timeFormat)) {
                System.out.println("You entered the wrong format!\n" +
                        "Example: 03:30");
                System.out.print("What time do you want to leave (Military Time) : ");
                departure = input.nextLine();
            }
        }
        //Creating ETA by using the Time class
        //Using a random range from 11:59PM (in milliseconds) to 12:01 AM (in milliseconds)
        long milli = (long)Math.floor((Math.random()*(86399999 - 59999))+ 59999);
        Time time = new Time(milli);
        String eta = time.toString().substring(0,5);

        double randomPrice = Math.floor(((100.0 + Math.random( ) * 1000.0) * 10)/10);
        System.out.println(randomPrice);
        //Reduces price based on if the person:
        //Age >= 60
        //Age <= 12
        //Gender = Female
        if(person.isOld()){
            randomPrice -= randomPrice*.6;
        }else if(person.isGirl()){
            randomPrice -= randomPrice*.25;
        }else if(person.isYoung()){
            randomPrice -= randomPrice*.5;
        }
        System.out.println(randomPrice);
        String finalPrice = String.format("%.2f", randomPrice);
        return new Pass(passNum, date, origin, destination, eta, departure, Double.parseDouble(finalPrice));
    }

    public static void writeIntoFile(Person user, Pass pass){
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
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static Boolean isUniqueNum(int num){
        try{
            File file = new File("passenger_log.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                List<String> line = Arrays.asList(scan.nextLine().split(","));
                Integer number = Integer.parseInt(line.get(0));
                if(number.equals(num)){
                    return false;
                }
            }
        }catch(IOException e){
            System.out.println(e);
        }
        return true;
    }

    public static String output(int passNum){
        try{
            File file = new File("passenger_log.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
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
        }catch(IOException e){
            System.out.println(e);
        }
        return null;
    }

    public static Boolean dateCheck(String date){
        List<String> dateSplit = Arrays.asList(date.split("/"));
        if(Integer.parseInt(dateSplit.get(0)) > 12 || Integer.parseInt(dateSplit.get(0)) < 1){
            return false;
        }else if(Integer.parseInt(dateSplit.get(1)) > 31 || Integer.parseInt(dateSplit.get(1)) < 1 ||
                (Integer.parseInt(dateSplit.get(0)) == 2 && Integer.parseInt(dateSplit.get(1)) > 29)){
            return false;
        }
        return true;
    }
}
