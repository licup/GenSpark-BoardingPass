import java.io.Serializable;

public class Pass implements Serializable {
    private int passNum;
    private String date;
    private String origin;
    private String destination;
    private String eta;
    private String departure;
    private double price;

    //Default Constructor
    public Pass(){

    }

    //Overloaded Constructor
    public Pass(int passNum, String date, String origin, String destination, String eta, String departure, double price) {
        this.passNum = passNum;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.eta = eta;
        this.departure = departure;
        this.price = price;
    }

    //Getter and Setter
    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pass{" +
                "passNum=" + passNum +
                ", date='" + date + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", eta='" + eta + '\'' +
                ", departure='" + departure + '\'' +
                ", price='" + price + "\'" +
                '}';
    }
}
