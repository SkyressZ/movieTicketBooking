 package movieticketbooking;
import java.util.HashMap;
import java.util.Map;
import static movieticketbooking.Manage.updateTicketsInFile;
public class Screen {
    private String theatreName, screenName, movieName;
    private HashMap<String,HashMap<String,Integer>> seatAvailability = new HashMap<String,HashMap<String,Integer>>();

    public String getTheatreName() {
        return theatreName;
    }

    public HashMap<String, HashMap<String, Integer>> getSeatAvailability() {  
        return seatAvailability;
    }

    public void setSeatAvailability(String timing,String seatClass,Integer seats) {
        if(this.seatAvailability.get(timing)!=null){
            HashMap<String,Integer> hm = this.seatAvailability.get(timing);
            if(hm.get(seatClass)==null){
                 hm.put(seatClass,seats);
                this.seatAvailability.put(timing,hm);
            }
            else{
                hm.put(seatClass,seats);
            }
        }
        else{
            HashMap<String,Integer> hm = new HashMap<String,Integer>();
            hm.put(seatClass,seats);
            this.seatAvailability.put(timing,hm);
        }
    }  
    
    
    public void updateSeatAvailability(String Date,String theatre,String timing,String seatClass,Integer seats) throws Exception{
        if(this.seatAvailability.get(timing)!=null){
            HashMap<String,Integer> hm = this.seatAvailability.get(timing);
            if(hm.get(seatClass)!=null){
                 hm.put(seatClass,hm.get(seatClass)+seats);
                 updateTicketsInFile(Date,theatreName,screenName,timing,seatClass,hm.get(seatClass));
            }
        }
    }
    

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Screen(String theatreName, String screenName, String movieName) {
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.movieName = movieName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void displayAllSeats(){
        System.out.println("Screen :"+getScreenName());
        System.out.println("Movie :"+getMovieName());
        for(Map.Entry<String,HashMap<String,Integer>> hm : seatAvailability.entrySet()){
            System.out.println("Timing : "+hm.getKey());
            
            for(Map.Entry<String,Integer> hm1 : hm.getValue().entrySet()){
                System.out.println("Class : "+hm1.getKey()+"\nNo of Available Seats : "+hm1.getValue());                
            }
            System.out.println();
            System.out.println("____________________________________");
            System.out.println();
        }
        
    }
  
}
   