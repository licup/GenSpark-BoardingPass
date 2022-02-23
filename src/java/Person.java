import java.io.Serializable;

public class Person {
    private String name;
    private String email;
    private String phoneNum;
    private Character gender;
    private int age;

    //Default Constructor
    public Person(){

    }
    //Overloaded Constructor
    public Person(String name, String email, String phoneNum, Character gender, int age) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.age = age;
    }

    //Methods
    public Boolean isGirl(){
        return this.gender.equals('F');
    }
    public Boolean isYoung(){
        return this.age <= 12;
    }
    public Boolean isOld(){
        return this.age >= 60;
    }

    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
