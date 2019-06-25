package movieticketbooking;

public class Booking {
    private Theatre theatre;
    private String userName;
    private String bookingId;
    private String timing,Date,screenName,movie;
    private int countBox = 0, countBalcony = 0, countElite = 0, countGold = 0, countSilver = 0;

    public Booking(String userName, Theatre theatre, String bookingId, String Date, String screenName, String timing, String movie) {
        
        this.userName = userName;
        this.theatre = theatre;
        this.bookingId = bookingId;
        this.timing = timing;
        this.Date = Date;
        this.screenName = screenName;
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getCountBox() {
        return countBox;
    }

    public void setCountBox(int countBox) {
        this.countBox = countBox;
    }

    public int getCountBalcony() {
        return countBalcony;
    }

    public void setCountBalcony(int countBalcony) {
        this.countBalcony = countBalcony;
    }

    public int getCountElite() {
        return countElite;
    }

    public void setCountElite(int countElite) {
        this.countElite = countElite;
    }

    public int getCountGold() {
        return countGold;
    }

    public void setCountGold(int countGold) {
        this.countGold = countGold;
    }

    public int getCountSilver() {
        return countSilver;
    }

    public void setCountSilver(int countSilver) {
        this.countSilver = countSilver;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
    
    
    public String toString(){
        String t = String.format("Booking ID : %s\nMovie : %s\nDate : %s\nTiming : %s\nScreen : %s\nTickets:\n",bookingId,movie,Date,timing,screenName,timing);
        if(countBox > 0){
            t+=String.format("Box Class : %d\n",countBox);
        }
        if(countBalcony > 0){ 
            t+=String.format("Balcony Class : %d\n",countBalcony);
        }
        if(countElite > 0){
            t+=String.format("ELite Class : %d\n",countElite);
        }
        if(countGold > 0){
            t+=String.format("Gold Class : %d\n",countGold);
        }
        if(countSilver > 0){
            t+=String.format("Silver Class : %d\n",countSilver);
        }
            
        return t;
    }
}
