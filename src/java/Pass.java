public class Pass {
    String passNum;
    String date;
    String origin;
    String destination;
    String eta;
    String departure;

    //Default Constructor
    public Pass(){

    }

    //Overloaded Constructor
    public Pass(String passNum, String date, String origin, String destination, String eta, String departure) {
        this.passNum = passNum;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.eta = eta;
        this.departure = departure;
    }

    //Getter and Setter
    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
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
}
